package com.caito.paymenthain.transactions.service.impl;

import com.caito.paymenthain.transactions.entity.Transaction;
import com.caito.paymenthain.transactions.repository.TransactionRepository;
import com.caito.paymenthain.transactions.service.contract.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caito Vilas
 */

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Transaction save(Transaction transaction) {
        log.info("inicio servicio guardar transaccion");
        log.info("guardando transaccion...");
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getOne(Long id) {
        log.info("inicio servicio buscar transaccion por id");
        log.info("buscando transaccion...");
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        log.info("inicio servicio busscar transacciones");
        log.info("buscando transacciones...");
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getForIbanAccount(String ibanAccount) {
        log.info("inicio servicio buscar transacciones por ibanAccount");
        log.info("buscando transacciones...");
        return transactionRepository.findByIbanAccount(ibanAccount);
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null)
            transactionRepository.deleteById(id);
    }
}
