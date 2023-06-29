package com.pizzaorderingsystem.service;

import com.pizzaorderingsystem.model.Customer;
import com.pizzaorderingsystem.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer updateCustomer(UUID customerId, Customer customer) {
        Customer customerData = customerRepository.findById(customerId).get();
        customerData = new Customer(customerId, customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getPhoneNumber(), customer.getEmailAddress());
        return customerRepository.save(customerData);
    }

    public void deleteCustomerById(UUID customerId) {
        customerRepository.deleteById(customerId);
    }
}
