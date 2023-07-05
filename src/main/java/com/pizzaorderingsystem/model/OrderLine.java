package com.pizzaorderingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
//@Data  //Dont use @Data cause in case of mapping the toString() also considers the objects used for mappings so it cause recursion of data issue and gives a stackoverflow
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderLineId;
    @NotNull
    private String size;
    private Integer quantity;
    private Long orderLineTotalPrice;

    //Mappings
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;
}
