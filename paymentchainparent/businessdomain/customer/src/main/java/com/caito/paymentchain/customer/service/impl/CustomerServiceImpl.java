package com.caito.paymentchain.customer.service.impl;

import com.caito.paymentchain.customer.entity.Customer;
import com.caito.paymentchain.customer.repository.CustomerRepository;
import com.caito.paymentchain.customer.service.contract.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author caito Vilas
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer save(Customer customer) {
        log.info("iniciando servicio guardar cliente");
        log.info("guardando cliente...");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getOne(Long id) {
        log.info("iniciando servicio buscar cliente por id");
        log.info("buscando cliente...");
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        log.info("iniciando servicio buscar todos los clientes");
        log.info("buscando clientes...");
        return customerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        log.info("iniciando servicio eliminar cliente");
        log.info("eliminando cliente");
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null)
            customerRepository.deleteById(id);
    }
}
