package com.pizzaorderingsystem.service;

import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.repo.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    public OrderLine addOrderLine(OrderLine orderLine){
        return orderLineRepository.save(orderLine);
    }
}
