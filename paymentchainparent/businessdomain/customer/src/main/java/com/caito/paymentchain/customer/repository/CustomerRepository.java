package com.caito.paymentchain.customer.repository;

import com.caito.paymentchain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author caito Vilas
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
