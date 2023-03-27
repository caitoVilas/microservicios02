package com.caito.paymentchain.customer.repository;

import com.caito.paymentchain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * author caito Vilas
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.code=?1")
    Customer findBYCode(String code);
   // Customer findByIban(String iban);
}
