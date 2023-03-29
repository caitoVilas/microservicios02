package com.caito.paymentchain.product.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * author caitoVilas
 */

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}
