package com.pizzaorderingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`order`")
@Getter
@Setter
//@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @Size(max = 30)
    private String status;//Default value should be CREATED
    @NotNull
    private Integer totalAmount;
    @NotNull
    private Date orderDateTime;
    @Size(max = 255)
    private String deliveryAddress;

    //Mappings
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    @JsonIgnore
    private Customer customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderLine> orderLineList = new ArrayList<>();

}
