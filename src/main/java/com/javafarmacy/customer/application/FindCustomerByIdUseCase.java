package com.javafarmacy.customer.application;

import java.util.Optional;

import com.javafarmacy.customer.domain.entity.Customer;
import com.javafarmacy.customer.domain.service.CustomerService;

public class FindCustomerByIdUseCase {
    private final CustomerService customerService;

    public FindCustomerByIdUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<Customer> execute(String id) {
        return customerService.findCustomerById(id);
    }
}