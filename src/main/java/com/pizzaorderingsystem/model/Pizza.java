package com.pizzaorderingsystem.model;

import com.pizzaorderingsystem.contants.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pizzaId;
    @NotBlank
    private String name;
    private String description;
    @Size(max = 10)
//    @Value(Constants.VEG)
    private String type;
    //    @Value(Constants.PIZZA_IMG_URL)
    private String imageUrl;
    @NotNull
    private Integer priceRegularSize;
    @NotNull
    private Integer priceMediumSize;
    @NotNull
    private Integer priceLargeSize;
    public Pizza(UUID pizzaId, String name, String description, String type, String imageUrl, @NotNull Integer priceRegularSize, @NotNull Integer priceMediumSize, @NotNull Integer priceLargeSize) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.imageUrl = imageUrl;
        this.priceRegularSize = priceRegularSize;
        this.priceMediumSize = priceMediumSize;
        this.priceLargeSize = priceLargeSize;
    }


    //Mappings
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    List<OrderLine> orderLineList = new ArrayList<>();


}
