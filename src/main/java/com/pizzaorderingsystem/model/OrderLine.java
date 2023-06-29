package com.pizzaorderingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderLineId;
    @NotNull
    private String size;
    private Integer quantity;
    private Long orderLineTotalPrice;

    //Mappings
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
