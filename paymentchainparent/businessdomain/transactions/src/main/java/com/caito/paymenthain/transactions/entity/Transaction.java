package com.caito.paymenthain.transactions.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author caito Vilas
 */

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String ibanAccount;
    @CreationTimestamp
    private LocalDateTime date;
    private Double amount;
    private Double fee;
    private String description;
    private String status;
    private String channel;
}
