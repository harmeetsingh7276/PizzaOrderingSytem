package com.pizzaorderingsystem.repo;

import com.pizzaorderingsystem.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PizzaRepository extends JpaRepository<Pizza, UUID> {

}
