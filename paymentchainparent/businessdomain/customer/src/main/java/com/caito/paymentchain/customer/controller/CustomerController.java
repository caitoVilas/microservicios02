package com.caito.paymentchain.customer.controller;

import com.caito.paymentchain.customer.entity.Customer;
import com.caito.paymentchain.customer.service.contract.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author caito Vilas
 */

@RestController
@RequestMapping("/api/v1/paymentchain/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.save(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getOne(@PathVariable Long id){
        Customer customer = customerService.getOne(id);
        if (customer == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = customerService.getAll();
        if (customers.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/full")
    public ResponseEntity<Customer> getFull(@RequestParam String code){
        Customer customer = customerService.getFull(code);
        if (customer == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer);
    }
}
