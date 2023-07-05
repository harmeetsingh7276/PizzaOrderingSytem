package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.dto.OrderDTO;
import com.pizzaorderingsystem.dto.OrderLineDTO;
import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.model.Pizza;
import com.pizzaorderingsystem.reponse.ApiResponse;
import com.pizzaorderingsystem.service.CustomerService;
import com.pizzaorderingsystem.service.OrderLineService;
import com.pizzaorderingsystem.service.OrderService;
import com.pizzaorderingsystem.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderService orderService;
    ApiResponse response;
    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/home")
    public String start() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(customerService.getCustomerById(orderDTO.getCustomerId()).get());
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setTotalAmount(orderDTO.getTotalAmount());
        log.info("BEFORE INSERT ORDER DATA");
        Order orderData = orderService.addOrder(order);

        for (OrderLineDTO orderLineDTO : orderDTO.getPizza()) {
            OrderLine orderLine = new OrderLine();
            Pizza pizzaData = pizzaService.getPizzaById(orderLineDTO.getPizzaId()).get();
            orderLine.setOrder(orderData);
            orderLine.setPizza(pizzaData);
            orderLine.setQuantity(orderLineDTO.getQuantity());
            orderLine.setSize(orderLineDTO.getSize());
            
            //setting totalOrderLinePrice
            if (orderLine.getSize().equals(Constants.REGULAR_SIZE)) {
                orderLine.setOrderLineTotalPrice(orderLine.getQuantity() * Long.parseLong(pizzaData.getPriceRegularSize().toString()));
            }
            if (orderLine.getSize().equals(Constants.MEDIUM_SIZE)) {
                orderLine.setOrderLineTotalPrice(orderLine.getQuantity() * Long.parseLong(pizzaData.getPriceMediumSize().toString()));
            }
            if (orderLine.getSize().equals(Constants.LARGE_SIZE)) {
                orderLine.setOrderLineTotalPrice(orderLine.getQuantity() * Long.parseLong(pizzaData.getPriceLargeSize().toString()));
            }
            OrderLine orderLineData = orderLineService.addOrderLine(orderLine);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_CREATED, orderData));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<Order> orderData = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, orderData));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable UUID orderId) {
        Optional<Order> orderData = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, orderData.get()));
    }
//
//    @PutMapping("/{customerId}")
//    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable UUID customerId,@RequestBody Customer customer) {
//        Customer customerData = orderService.updateCustomer(customerId,customer);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_UPDATED, customerData));
//    }
//
//    @DeleteMapping("/{customerId}")
//    public ResponseEntity<ApiResponse> deleteCustomerById(@PathVariable UUID customerId) {
//        orderService.deleteCustomerById(customerId);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_DELETED,new ArrayList<>()));
//    }
}
