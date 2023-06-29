package com.pizzaorderingsystem.service;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.model.Pizza;
import com.pizzaorderingsystem.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public Pizza setDefaults(Pizza pizza) {
        if (pizza.getImageUrl() == null || pizza.getImageUrl().isBlank()) {
            pizza.setImageUrl(Constants.PIZZA_IMG_URL);
        }
        if (pizza.getType() == null || pizza.getType().isBlank()) {
            pizza.setType(Constants.VEG);
        }
        return pizza;
    }

    public Pizza addPizza(Pizza pizza) {
        pizza = setDefaults(pizza);
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> getPizzaById(UUID pizzaId) {
        return pizzaRepository.findById(pizzaId);
    }

    public void deletePizzaById(UUID pizzaId) {
        pizzaRepository.deleteById(pizzaId);
    }

    public Pizza updatePizzaById(UUID pizzaId, Pizza pizza) {
        Pizza pizzaData = pizzaRepository.findById(pizzaId).get();
        pizzaData = new Pizza(pizzaId, pizza.getName(), pizza.getDescription(), pizza.getType(), pizza.getImageUrl(), pizza.getPriceRegularSize(), pizza.getPriceMediumSize(), pizza.getPriceLargeSize());
        pizzaData = setDefaults(pizzaData);
        return pizzaRepository.save(pizzaData);
    }
}
