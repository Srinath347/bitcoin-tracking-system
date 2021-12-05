package com.db.bts.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.db.bts.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.db.bts.enums.TransactionStatus;
import com.db.bts.mapper.TransactionDTOMapper;
import com.db.bts.model.BitcoinPriceModel;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.model.TransactionStatistics;
import com.db.bts.model.TransactionTimeModel;
import com.db.bts.model.UserTransactionAmountModel;
import com.db.bts.repository.TransactionRepository;
import com.db.bts.service.AccountService;
import com.db.bts.service.AddressService;
import com.db.bts.service.TransactionService;
import com.db.bts.service.UserService;
import com.google.gson.Gson;

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
    private AdminServiceImpl adminService;

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
    public List<Transaction> getTransactionByTraderId(int traderId) throws Exception {
        List<User> associatedUsersList = userService.findUsersByTraderId(traderId);
        logger.info("users associated with this Trader are : {}", associatedUsersList.size());
        return Optional.ofNullable(transactionRepository.getTransactionByUsers(associatedUsersList))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not fetch transactions"));
    }

    @Override
    public Transaction addTransaction(TransactionModel transactionDTO) throws Exception {

        User user = userService.findUserById(transactionDTO.getUserId());
        if (transactionDTO.getTraderId() != null && user.getTrader().getId() != transactionDTO.getTraderId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Trader is unauthorized to execute transaction on this users behalf");
        }

        if (transactionDTO.getTraderId() != null && !user.getTrader().getRole().getName().equalsIgnoreCase("trader")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Admin is not a trader");
        }

        Transaction transaction = new Transaction();
        float bitcoinValue = getBitcoinValue();
        float transactionBitcoins = transactionDTO.getBitcoin();
        float transactionValue = transactionBitcoins * bitcoinValue;
        String commissionType = transactionDTO.getCommissionType();
        float commissionValue = transactionValue * (getCommissionPercent(transactionDTO.getUserId())/100);
        float commissionBitcoins = transactionBitcoins * (getCommissionPercent(transactionDTO.getUserId())/100);

        Account account = accountService.findAccountByUserId(transactionDTO.getUserId());
        float existingBitcoins = account.getBitcoin();
        float existingBalance = account.getBalance();

        logger.info("Transaction value {}", transactionValue);
        if(transactionDTO.getType().equalsIgnoreCase("buy")) {
            transaction.setType("BUY");
            if (commissionType.equalsIgnoreCase("fiat currency")) {
                if (!checkIsCurrencyAvailable(transactionValue + commissionValue, transactionDTO.getUserId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Sufficient currency not available in account to make transaction");
                }
                existingBalance -= (transactionValue + commissionValue);
            }
            if (commissionType.equalsIgnoreCase("bitcoin")) {
                if(!checkIsBitcoinsAvailable(commissionBitcoins, transactionDTO.getUserId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sufficient bitcoins not available in account");
                }
                existingBitcoins -= commissionBitcoins;
            }
            existingBitcoins += transactionBitcoins;
        } else {
            transaction.setType("SELL");
            if(!checkIsBitcoinsAvailable(transactionBitcoins, transactionDTO.getUserId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sufficient bitcoins not available in account");
            }
            if (commissionType.equalsIgnoreCase("fiat currency")) {
                if (!checkIsCurrencyAvailable(commissionValue, transactionDTO.getUserId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Sufficient currency not available in account to make transaction");
                }
                existingBalance += (transactionValue - commissionValue);
                existingBitcoins -= transactionBitcoins;
            } else {
                if(!checkIsBitcoinsAvailable(transactionBitcoins + commissionBitcoins, transactionDTO.getUserId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sufficient bitcoins not available in account");
                }
                existingBalance += transactionValue;
                existingBitcoins -= (transactionBitcoins + commissionBitcoins);
            }
        }

        transaction.setUser(user);
        transaction.setRole(getUserRole(3));
        transaction.setStatus(TransactionStatus.ACTIVE.getValue());
        transaction.setAmount(transactionValue);
        transaction.setCommissionValue(commissionValue);
        transaction.setBitcoin(transactionBitcoins);
        transaction.setCommissionType(commissionType);

        if (transactionDTO.getTraderId() != null) {
            Admin trader = adminService.findAdminById(transactionDTO.getTraderId());
            transaction.setTrader(trader);
            transaction.setRole(trader.getRole());
        }
      
        logger.info("Transaction details {}", transaction);


        account.setBitcoin(existingBitcoins);
        account.setBalance(existingBalance);
        account = accountService.updateAccount(account.getId(), account);
        logger.info("Account details updated{}", account);
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
        return Optional.ofNullable(transactionRepository.findTransactionsByDate(from, to))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to fetch transactions"));
    }

    @Override
    public TransactionTimeModel findTransactionsStatisticsByDate(Date from, Date to) throws Exception {
        logger.info("Date: {}, {}", from, to);
        List<Transaction> transactions = Optional.ofNullable(transactionRepository.findTransactionsByDate(from, to))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to fetch transactions"));
        logger.info("Count: {}", transactions.size());
        List<TransactionStatistics> transactionStatistics = Optional.ofNullable(transactionRepository.findTransactionsStatisticsByDate(from, to))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to fetch transactions"));
        TransactionTimeModel transactionTimeModel = new TransactionTimeModel();
        transactionTimeModel.setTransactionStatistics(transactionStatistics);
        transactionTimeModel.setTransactionList(transactions);
        return transactionTimeModel;

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

    private Boolean checkIsBitcoinsAvailable(float bitcoins, int userId) throws Exception {
        Double bitcoinBalance = accountService.findBitcoinsByUserId(userId);
        logger.info("Bitcoins : {}", bitcoinBalance);
        return bitcoinBalance >= (bitcoins);
    }

    private float getBitcoinValue() {
        RestTemplate restTemplate = new RestTemplate();
        String btcPriceUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
        String response = restTemplate.getForObject(btcPriceUrl, String.class);
        Gson gson = new Gson();
        BitcoinPriceModel bitcoinPriceModel = gson.fromJson(response, BitcoinPriceModel.class);
        float currentValue = bitcoinPriceModel.getBpi().getUSD().getRate_float();
        logger.info("current bitcoin value {}", currentValue);
        return currentValue;
    }

    private float getCommissionPercent(int userId) throws Exception {
        User user = userService.findUserById(userId);
        return user.getMember().getCommissionRate();
    }

    private Role getUserRole(int roleId) {
        Role role  = new Role();
        role.setId(roleId);
        role.setName("client");
        return role;
    }

    private boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }

}
