package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.dto.OrderLineDTO;
import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.model.Pizza;
import com.pizzaorderingsystem.reponse.ApiResponse;
import com.pizzaorderingsystem.service.OrderLineService;
import com.pizzaorderingsystem.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orderlines")
@Slf4j
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;


    ApiResponse response;

    @GetMapping("/home")
    public String start() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOrderLine(@RequestBody OrderLineDTO orderLineDto) {
        log.info("INSIDE");
        OrderLine orderLineData = orderLineService.addOrderLine(orderLineDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_CREATED, orderLineData));
    }
}
