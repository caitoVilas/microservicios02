package com.caito.paymentchain.customer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    private String code;
    private String phone;
    private String iban;
    private String surname;
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerProduct> products;
    @Transient
    private List<?> transactions;
}
