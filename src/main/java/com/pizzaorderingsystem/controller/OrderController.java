package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.dto.OrderDTO;
import com.pizzaorderingsystem.dto.OrderLineDTO;
import com.pizzaorderingsystem.model.Customer;
import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.model.Pizza;
import com.pizzaorderingsystem.reponse.ApiResponse;
import com.pizzaorderingsystem.reponse.OrderResponse;
import com.pizzaorderingsystem.service.CustomerService;
import com.pizzaorderingsystem.service.OrderLineService;
import com.pizzaorderingsystem.service.OrderService;
import com.pizzaorderingsystem.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//    @PostMapping
//    public ResponseEntity<ApiResponse> addOrder(@RequestBody Order order) {
//        Order orderData = orderService.addOrder(order);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_CREATED, orderData));
//    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(customerService.getCustomerById(orderDTO.getCustomerId()).get());
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setTotalAmount(orderDTO.getTotalAmount());
        log.info("BEFORE INSERT ORDER DATA");
        Order orderData = orderService.addOrder(order);


//        List<OrderLine> orderLineList = new ArrayList<>();
        //Set all the OrderLines here for the orderList
        for (OrderLineDTO orderLineDTO : orderDTO.getPizza()) {
            OrderLine orderLine = new OrderLine();
//            log.info(orderLineDTO.getPizzaId().toString());
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
            log.info("BEFORE INSERT ORDERLINE DATA");
            OrderLine orderLineData = orderLineService.addOrderLine(orderLine);
            log.info("AFTER INSERT ORDERLINE DATA:{}", orderLineData.getOrderLineId());
//            orderLineList.add(orderLineData);
        }

//        order.setOrderLineList(orderLineList);
//        OrderResponse orderResponse = new OrderResponse(orderData);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_CREATED, orderData));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<Order> orderData = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, orderData));
    }
//
//    @GetMapping("/{customerId}")
//    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable UUID customerId) {
//        Optional<Customer> customerData = orderService.getCustomerById(customerId);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, customerData.get()));
//    }
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
