package com.pizzaorderingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class OrderLineDTO {
    @NotNull
    private UUID pizzaId;
    @NotNull
    private String size;
    @NotNull
    private Integer quantity;

}
