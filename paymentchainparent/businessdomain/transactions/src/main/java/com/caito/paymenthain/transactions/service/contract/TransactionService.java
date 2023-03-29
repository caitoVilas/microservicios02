package com.caito.paymenthain.transactions.service.contract;

import com.caito.paymenthain.transactions.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Transaction getOne(Long id);
    List<Transaction> getAll();
    List<Transaction> getForIbanAccount(String ibanAccount);
    void delete(Long id);
}
