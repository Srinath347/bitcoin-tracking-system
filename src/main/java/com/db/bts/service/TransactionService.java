package com.db.bts.service;

import com.db.bts.entity.Transaction;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.model.TransactionTimeModel;
import com.db.bts.model.UserTransactionAmountModel;

import java.util.Date;
import java.util.List;


public interface TransactionService {

    Transaction findTransactionById(int transactionId) throws Exception;

    Transaction save(Transaction transaction) throws Exception;

    void updateStatus(int transactionId, int status) throws Exception;

    List<Transaction> getTransactionByUserId(int userId) throws Exception;

    List<Transaction> getTransactionByTraderId(int traderId) throws Exception;

    Transaction addTransaction(TransactionModel transactionDTO) throws Exception;

    Float findAmountSumByUser(int userId) throws Exception;

    List<UserTransactionAmountModel> findAmountSum(Date from, Date to) throws Exception;

    List<TransactionSearchModel> findTransactionByCriteria(String value, String field) throws Exception;

    List<Transaction> findTransactionsByDate(Date from, Date to) throws Exception;

    TransactionTimeModel findTransactionsStatisticsByDate(Date from, Date to) throws Exception;

}
