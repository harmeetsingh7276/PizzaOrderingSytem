package com.pizzaorderingsystem.repo;

import com.pizzaorderingsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
