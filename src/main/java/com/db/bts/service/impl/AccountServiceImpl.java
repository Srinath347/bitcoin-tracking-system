package com.db.bts.service.impl;

import com.db.bts.entity.Account;
import com.db.bts.entity.Payment;
import com.db.bts.repository.AccountRepository;
import com.db.bts.service.AccountService;
import com.db.bts.service.PaymentService;
import com.db.bts.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Override
    // TODO userId
    public Account findAccountById(int userId) throws Exception {
        Account account = accountRepository.findAccountByUserId(userId);
        if (account != null) {
            return account;
        } else {
            logger.error("Account not found for user id: {}", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested user account not found");
        }
        //return accountRepository.findById(accountId).orElseThrow(Exception::new);
    }

    @Override
    public Account addAccount(Account account) throws Exception {
        try {
            return accountRepository.save(account);
        } catch (Exception e) {
            logger.error("Account creation failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create and save account");
        }

    }

    @Override
    public Account updateAccount(int userId, Account account) throws Exception {

        try {
            Account existingAccount = findAccountByUserId(userId);
            account = validateAndUpdateAccountAttributes(existingAccount, account);
            return accountRepository.save(account);
        } catch (Exception e) {
            logger.error("account details update failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void deleteAccountById(int accountId) throws Exception {
        accountRepository.deleteById(accountId);
    }

    @Override
    public Account findAccountByUserId(int userId) throws Exception {
        return Optional.ofNullable(accountRepository.findAccountByUserId(userId))
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to get account details"));
    }

    @Override
    public Double findBalanceByUserId(int userId) throws Exception {
        return Optional.ofNullable(accountRepository.findBalanceByUserId(userId))
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to get account details"));
    }

    @Override
    public Double findBitcoinsByUserId(int userId) throws Exception {
        return Optional.ofNullable(accountRepository.findBitcoinsByUserId(userId))
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to get account details"));
    }

    private Account validateAndUpdateAccountAttributes(Account existingAccount, Account account) throws Exception {
        Account updatedAccount = existingAccount;
        if((existingAccount.getId()) != (account.getId())) {
            throw new Exception("Could not update account");
        }
        if(account.getUser()!=null) {
            updatedAccount.setUser(account.getUser());
        }
        updatedAccount.setBalance(account.getBalance());
        updatedAccount.setBitcoin(account.getBitcoin());

        return updatedAccount;
    }

    @Override
    public Account addAmountToUserAccount(int userId, float amount) throws Exception {
        Account existingAccount = accountRepository.findAccountByUserId(userId);
        logger.info("existing account {}", existingAccount);
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setUser(userService.findUserById(userId));
//        Integer traderId = userService.findTraderByUserId(userId);
//        logger.info("traderId: {}", traderId);
        payment.setTrader(userService.findTraderByUserId(userId));
        Payment payment1 = paymentService.save(payment);
//        float updatedBalance = existingAccount.getBalance() + amount;
//        existingAccount.setBalance(updatedBalance);
//        return accountRepository.save(existingAccount);
        return existingAccount;
    }

    @Override
    public Account addAmountByUserId(int userId, float amount) throws Exception {
        Account existingAccount = accountRepository.findAccountByUserId(userId);
        logger.info("existing account {}", existingAccount);
        float updatedBalance = existingAccount.getBalance() + amount;
        existingAccount.setBalance(updatedBalance);
        return accountRepository.save(existingAccount);
    }
}
