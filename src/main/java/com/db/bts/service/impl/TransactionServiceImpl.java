package com.db.bts.service.impl;


import com.db.bts.entity.Transaction;
import com.db.bts.repository.TransactionRepository;
import com.db.bts.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction findTransactionById(int transactionId) throws Exception {
        return transactionRepository.findById(transactionId)
                .orElseThrow(Exception::new);
    }

    @Override
    public Transaction save(Transaction transaction) throws Exception {
        return transactionRepository.save(transaction);
    }

    @Override
    public void updateStatus(int id, int status) throws Exception {
        transactionRepository.updateStatus(id, status);
    }

    @Override
    public List<Transaction> getTransactionByUserId(int userId) throws Exception {
        return transactionRepository.getTransactionByUserId(userId);
    }
}
