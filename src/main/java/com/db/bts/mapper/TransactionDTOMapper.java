package com.db.bts.mapper;

import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.model.TransactionModel;
import com.db.bts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
public class TransactionDTOMapper {

    @Autowired
    UserService userService;

    public Transaction mapTransactionDTOToModel(TransactionModel transactionDTO) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCommissionType(transactionDTO.getCommissionType());
        transaction.setCommissionValue(transactionDTO.getCommissionValue());
        Timestamp timestamp = new Timestamp(new Date().getTime());
        transaction.setTime(timestamp);
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setType(transactionDTO.getType());
        User user = Optional.ofNullable(userService.findUserById(transactionDTO.getUserId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested user not found"));
        transaction.setUser(user);
        return transaction;
    }
}
