package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.repo.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderLineController {
    @Autowired
    private OrderLineRepository orderLineRepository;

    @GetMapping("{orderId}")
    public List<OrderLine> getAllOrderLinesByOrderId(@PathVariable UUID orderId) {
//        return orderLineRepository.
        return new ArrayList<>();
    }
}
