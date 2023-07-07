package com.pizzaorderingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderLineDTO {
    @NotNull
    private UUID pizzaId;
    @NotNull
    private String size;
    @NotNull
    private Integer quantity;

}
