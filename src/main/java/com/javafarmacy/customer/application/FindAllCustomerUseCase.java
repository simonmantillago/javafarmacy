package com.javafarmacy.customer.application;

import java.util.List;

import com.javafarmacy.customer.domain.entity.Customer;
import com.javafarmacy.customer.domain.service.CustomerService;

public class FindAllCustomerUseCase {
    private final CustomerService customerService;

    public FindAllCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> execute() {
        return customerService.findAllCustomer();
    }
}