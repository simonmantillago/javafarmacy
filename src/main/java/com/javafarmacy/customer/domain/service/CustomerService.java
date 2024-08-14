package com.javafarmacy.customer.domain.service;

import java.util.List;
import java.util.Optional;

import com.javafarmacy.customer.domain.entity.Customer;



public interface CustomerService {
    void createCustomer(Customer Customer);
    void updateCustomer(Customer Customer);
    Customer deleteCustomer(String id);
    Optional<Customer> findCustomerById(String id);
    List<Customer> findAllCustomer();
    
}
