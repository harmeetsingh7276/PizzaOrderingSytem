package com.pizzaorderingsystem.service;

import com.pizzaorderingsystem.dto.OrderDTO;
import com.pizzaorderingsystem.dto.OrderLineDTO;
import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.model.OrderLine;
import com.pizzaorderingsystem.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
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

    public Optional<Order> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrderById(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(UUID orderId, OrderDTO orderDTO) {
        Order orderData = orderRepository.findById(orderId).get();
        List<OrderLine> orderLineList=orderData.getOrderLineList();


        orderData.setDeliveryAddress(orderDTO.getDeliveryAddress());
        for (OrderLineDTO orderLineDTO : orderDTO.getPizza()) {
            log.info("Pizza Id to be updated:{}", orderLineDTO.getPizzaId());
            OrderLine orderLineData= (OrderLine) orderLineList.stream().filter(orderLine -> orderLine.getPizza().getPizzaId()==orderLineDTO.getPizzaId());
//            for (OrderLine orderLine : orderLineList) {
//                if (orderLineDTO.getPizzaId() == orderLine.getPizza().getPizzaId()) {
//                    orderData.set
//                }
//            }
        }
//        orderData=new Order;
        return orderRepository.save(orderData);

    }
}
