package com.caito.paymenthain.transactions.controller;

import com.caito.paymenthain.transactions.entity.Transaction;
import com.caito.paymenthain.transactions.service.contract.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author caito Vilas
 */

@RestController
@RequestMapping("/api/v1/paymentchain/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getOne(@PathVariable Long id){
        Transaction transaction = transactionService.getOne(id);
        if (transaction == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(){
        List<Transaction> transactions = transactionService.getAll();
        if (transactions.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/customer/transactions")
    public ResponseEntity<List<Transaction>> getForIbanAccount(@RequestParam String ibanAccount){
        return ResponseEntity.ok(transactionService.getForIbanAccount(ibanAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        transactionService.delete(id);
        return ResponseEntity.ok(null);
    }
}
