package com.caito.paymentchain.customer.service.contract;

import com.caito.paymentchain.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    Customer getOne(Long id);
    List<Customer> getAll();
    void delete(Long id);
}
