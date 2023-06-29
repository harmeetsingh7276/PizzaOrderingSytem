package com.pizzaorderingsystem.repo;

import com.pizzaorderingsystem.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
}
