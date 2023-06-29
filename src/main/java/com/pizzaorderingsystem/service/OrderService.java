package com.pizzaorderingsystem.service;

import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order order) {
        order.setOrderDateTime(new Date());
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
