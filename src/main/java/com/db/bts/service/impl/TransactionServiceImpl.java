package com.db.bts.service.impl;


import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.mapper.TransactionDTOMapper;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.UserTransactionAmountModel;
import com.db.bts.repository.TransactionRepository;
import com.db.bts.service.TransactionService;
import com.db.bts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDTOMapper transactionDTOMapper;

    @Autowired
    private UserService userService;

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
        User user = userService.findUserById(userId);
        return Optional.ofNullable(transactionRepository.getTransactionByUser(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not fetch transactions"));
    }

    @Override
    public Transaction addTransaction(TransactionModel transactionDTO) throws Exception {
        Transaction transaction = transactionDTOMapper.mapTransactionDTOToModel(transactionDTO);
        return Optional.ofNullable(transactionRepository.save(transaction))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create transaction"));
    }

    @Override
    public Float findAmountSumByUser(int userId) throws Exception {
        User user = userService.findUserById(userId);
        return Optional.ofNullable(transactionRepository.findAmountSumByUser(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not get amount"));
    }

    @Override
    public List<UserTransactionAmountModel> findAmountSum() throws Exception {
//        User user = userService.findUserById();
        List<?> results =  Optional.ofNullable(transactionRepository.findAmountSum())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not get amount"));
        UserTransactionAmountModel userTransactionAmountModel = new UserTransactionAmountModel(100, 2);
        List<UserTransactionAmountModel> userTransactionAmountModels = new ArrayList<>();
        userTransactionAmountModels.add(userTransactionAmountModel);
        return userTransactionAmountModels;
    }
}
