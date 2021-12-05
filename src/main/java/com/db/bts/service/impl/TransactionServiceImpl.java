package com.db.bts.service.impl;


import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.mapper.TransactionDTOMapper;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.model.UserTransactionAmountModel;
import com.db.bts.repository.TransactionRepository;
import com.db.bts.service.AccountService;
import com.db.bts.service.AddressService;
import com.db.bts.service.TransactionService;
import com.db.bts.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    @Autowired
    private AddressService addressService;

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
            float amount = transactionDTO.getAmount();
            if(transactionDTO.getCommissionType().equalsIgnoreCase("bitcoin")) {
                amount += transactionDTO.getCommissionValue();
            } else {
                if(!checkIsCurrencyAvailable(transactionDTO.getCommissionValue(), transactionDTO.getUserId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sufficient currency not available in account to make transaction");
                }
            }
            if(!checkIsBitcoinsAvailable(amount, transactionDTO.getUserId())) {
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

    @Override
    public List<TransactionSearchModel> findTransactionByCriteria(String value, String field) throws Exception {
        List<TransactionSearchModel> transactionSearchModels = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        logger.info("Field: {}, Value: {}", field, value);
        if(field.equalsIgnoreCase("name")) {
            transactions =  findTransactionByUserName(value);
        }
        if(field.equalsIgnoreCase("type")) {
            transactions =  findTransactionByType(value);
        }
        if(field.equalsIgnoreCase("email")) {
            transactions =  findTransactionsByEmail(value);
        }
        if(field.equalsIgnoreCase("streetAddress")) {
            List<Integer> userIds = addressService.findUserIdByStreetAddress(value);
            transactions =  findTransactionsByUserIds(userIds);
        }
        if(field.equalsIgnoreCase("city")) {
            List<Integer> userIds = addressService.findUserIdByCity(value);
            transactions =  findTransactionsByUserIds(userIds);
        }
        if(transactions.size() > 0) {
            for ( Transaction transaction : transactions) {
                logger.info("Transactions {}", transaction.getId());
                TransactionSearchModel transactionSearchModel = new TransactionSearchModel();
                transactionSearchModel.setTime(transaction.getTime());
                transactionSearchModel.setAmount(transaction.getAmount());
                transactionSearchModel.setCommissionType(transaction.getCommissionType());
                transactionSearchModel.setUserName(transaction.getUser().getFirstName()+" "+ transaction.getUser().getLastName());
                transactionSearchModel.setCommissionValue(transaction.getCommissionValue());
                transactionSearchModels.add(transactionSearchModel);
            }
        return transactionSearchModels;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no transactions found for " + field + ": " + value);
    }

    @Override
    public List<Transaction> findTransactionsByDate(Date from, Date to) throws Exception {
        logger.info("Date: {}, {}", from, to);
        List<Transaction> transactions = Optional.ofNullable(transactionRepository.findTransactionsByDate(from, to))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to fetch transactions"));
        logger.info("Count: {}", transactions.size());
        return transactions;
    }

    private List<Transaction> findTransactionByUserName(String name) throws Exception{
        return Optional.ofNullable(transactionRepository.findTransactionsByUserName(name))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No transactions found for user"));
    }

    private List<Transaction> findTransactionByType(String type) throws Exception{
        return Optional.ofNullable(transactionRepository.findTransactionsByType(type))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No transactions found for type: " + type));
    }

    private List<Transaction> findTransactionsByEmail(String email) throws Exception{
        return Optional.ofNullable(transactionRepository.findTransactionsByEmail(email))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No transactions found for email: " + email));
    }

    private List<Transaction> findTransactionsByUserIds(List<Integer> userIds) throws Exception{
        return Optional.ofNullable(transactionRepository.findTransactionsByUserIds(userIds))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No transactions found for street address"));
    }

    private Boolean checkIsCurrencyAvailable(float amount, int userId) throws Exception {
        Double currencyBalance = accountService.findBalanceByUserId(userId);
        logger.info("Balance : {}", currencyBalance);
        return currencyBalance >= (amount);
    }

    private Boolean checkIsBitcoinsAvailable(float amount, int userId) throws Exception {
        Double bitcoinBalance = accountService.findBitcoinsByUserId(userId);
        logger.info("Bitcoins : {}", bitcoinBalance);
        return bitcoinBalance >= (amount);
    }

    private boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }

}
