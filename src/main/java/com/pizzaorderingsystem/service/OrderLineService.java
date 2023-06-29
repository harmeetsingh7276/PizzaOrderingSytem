package com.pizzaorderingsystem.service;

import com.pizzaorderingsystem.dto.OrderLineDTO;
import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.model.Pizza;
import com.pizzaorderingsystem.repo.OrderLineRepository;
import com.pizzaorderingsystem.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    public OrderLine addOrderLine(OrderLineDTO orderLineDto) {
        OrderLine orderLine = new OrderLine();
        Pizza pizza = pizzaRepository.findById(orderLineDto.getPizza()).get();
        orderLine.setPizza(pizza);
        orderLine.setSize(orderLineDto.getSize());
        orderLine.setQuantity(orderLineDto.getQuantity());
        long subTotal = 0;
        if (orderLine.getSize().equals("regular")) {
            subTotal = pizza.getPriceRegularSize() * orderLine.getQuantity();
            orderLine.setOrderLineTotalPrice(subTotal);
        }
        if (orderLine.getSize().equals("medium")) {
            subTotal = pizza.getPriceMediumSize() * orderLine.getQuantity();
            orderLine.setOrderLineTotalPrice(subTotal);
        }
        if (orderLine.getSize().equals("large")) {
            subTotal = pizza.getPriceLargeSize() * orderLine.getQuantity();
            orderLine.setOrderLineTotalPrice(subTotal);
        }
        return orderLineRepository.save(orderLine);
    }

}
