package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.model.Customer;
import com.pizzaorderingsystem.reponse.ApiResponse;
import com.pizzaorderingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    ApiResponse response;

    @GetMapping("/home")
    public String start() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody Customer customer) {
        Customer customerData = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_CREATED, customerData));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<Customer> customerData = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, customerData));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable UUID customerId) {
        Optional<Customer> customerData = customerService.getCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, customerData.get()));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
        Customer customerData = customerService.updateCustomer(customerId, customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_UPDATED, customerData));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomerById(@PathVariable UUID customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_DELETED, new ArrayList<>()));
    }
}
