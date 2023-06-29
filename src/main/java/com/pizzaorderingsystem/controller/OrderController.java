package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.reponse.ApiResponse;
import com.pizzaorderingsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    ApiResponse response;

    @GetMapping("/home")
    public String start() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@RequestBody Order order) {
        Order orderData = orderService.addOrder(order);
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
