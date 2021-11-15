package com.db.bts.service;

import com.db.bts.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction findTransactionById(int transactionId) throws Exception;

    Transaction save(Transaction transaction) throws Exception;

    void updateStatus(int transactionId, int status) throws Exception;

    List<Transaction> getTransactionByUserId(int userId) throws Exception;
}
