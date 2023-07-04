package com.pizzaorderingsystem.reponse;

import com.pizzaorderingsystem.model.Customer;
import com.pizzaorderingsystem.model.Order;
import com.pizzaorderingsystem.model.OrderLine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderResponse {
    private UUID orderId;
    private String status;
    private Integer totalAmount;
    private Date orderDateTime;
    private String deliveryAddress;

    private Customer customerId;
    private List<OrderLine> orderLineList = new ArrayList<>();

    public OrderResponse(Order orderData) {
        this.customerId = orderData.getCustomerId();
        this.orderDateTime = orderData.getOrderDateTime();
        this.orderId = orderData.getOrderId();
        this.status = orderData.getStatus();
        this.deliveryAddress = orderData.getDeliveryAddress();
        this.orderLineList = orderData.getOrderLineList();
        this.totalAmount = orderData.getTotalAmount();
    }
}
