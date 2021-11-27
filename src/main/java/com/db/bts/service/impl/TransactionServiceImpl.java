package com.db.bts.service.impl;


import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.mapper.TransactionDTOMapper;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.UserTransactionAmountModel;
import com.db.bts.repository.TransactionRepository;
import com.db.bts.service.AccountService;
import com.db.bts.service.TransactionService;
import com.db.bts.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDTOMapper transactionDTOMapper;

    @Autowired
    private UserService userService;

    @Autowired
    AccountService accountService;

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
        if(transactionDTO.getType().equalsIgnoreCase("sell")) {
            if(!checkIsBitcoinsAvailable(transactionDTO)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sufficient bitcoins not available in account");
            }
        }
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
    public List<UserTransactionAmountModel> findAmountSum(Date from, Date to) throws Exception {
        return Optional.ofNullable(transactionRepository.findAmountSum(from, to))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not get amount"));
    }

    private Boolean checkIsBitcoinsAvailable(TransactionModel transactionDTO) throws Exception {
        Double bitcoinBalance = accountService.findBitcoinsByUserId(transactionDTO.getUserId());
        logger.info("Bitcoins : {}", bitcoinBalance);
        if (transactionDTO.getCommissionType().equalsIgnoreCase("bitcoin")) {
            return bitcoinBalance >= (transactionDTO.getAmount() + transactionDTO.getCommissionValue());
        } else {
            return bitcoinBalance >= (transactionDTO.getAmount());
        }
    }

}
