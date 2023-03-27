package com.caito.paymenthain.transactions.repository;

import com.caito.paymenthain.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author caito Vilas
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.ibanAccount= ?1")
    List<Transaction> findByIbanAccount(String ibanAccount);
}