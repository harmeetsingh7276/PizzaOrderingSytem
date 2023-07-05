package com.pizzaorderingsystem.controller;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.model.Pizza;
import com.pizzaorderingsystem.reponse.ApiResponse;
import com.pizzaorderingsystem.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;
    ApiResponse response;

    @GetMapping("/home")
    public String start() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPizza(@RequestBody Pizza pizza) {
        Pizza pizzaData = pizzaService.addPizza(pizza);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_CREATED, pizzaData));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPizza() {
        List<Pizza> pizzaData = pizzaService.getAllPizza();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, pizzaData));
    }

    @GetMapping("/{pizzaId}")
    public ResponseEntity<ApiResponse> getAllPizza(@PathVariable UUID pizzaId) {
        Optional<Pizza> pizzaData = pizzaService.getPizzaById(pizzaId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_FOUND, pizzaData.get()));
    }

    @PutMapping("/{pizzaId}")
    public ResponseEntity<ApiResponse> updatePizza(@PathVariable UUID pizzaId, @RequestBody Pizza pizza) {
        Pizza pizzaData = pizzaService.updatePizzaById(pizzaId, pizza);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_UPDATED, pizzaData));
    }

    @DeleteMapping("/{pizzaId}")
    public ResponseEntity<ApiResponse> deletePizzaById(@PathVariable UUID pizzaId) {
        pizzaService.deletePizzaById(pizzaId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, Constants.DATA_DELETED, new ArrayList<>()));
    }
}
