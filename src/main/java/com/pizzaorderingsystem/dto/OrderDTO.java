package com.pizzaorderingsystem.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID customerId;
    private String deliveryAddress;
    private Integer totalAmount;
    private List<OrderLineDTO> pizza;
}
