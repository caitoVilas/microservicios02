package com.caito.paymentchain.product.repository;

import com.caito.paymentchain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author caito Vilas
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}