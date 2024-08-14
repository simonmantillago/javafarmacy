package com.javafarmacy.customer.application;

import com.javafarmacy.customer.domain.entity.Customer;
import com.javafarmacy.customer.domain.service.CustomerService;

public class DeleteCustomerUseCase {
    private final CustomerService customerService;

    public DeleteCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer execute(String customerId) {
        return customerService.deleteCustomer(customerId);
    }
}