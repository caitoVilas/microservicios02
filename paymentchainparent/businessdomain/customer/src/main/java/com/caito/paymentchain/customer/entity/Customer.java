package com.caito.paymentchain.customer.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * author caito Vilas
 */

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
}
