package com.javafarmacy.customer.application;

import com.javafarmacy.customer.domain.entity.Customer;
import com.javafarmacy.customer.domain.service.CustomerService;

public class UpdateCustomerUseCase {
    private final CustomerService customerService;

    public UpdateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer){
        customerService.updateCustomer(customer);
    }
}