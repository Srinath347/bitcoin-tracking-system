package com.db.bts.service.impl;

import com.db.bts.entity.Account;
import com.db.bts.entity.User;
import com.db.bts.repository.AccountRepository;
import com.db.bts.service.AccountService;
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

    @Override
    public Account findAccountById(int accountId) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            logger.error("Account not found for id: {}", accountId);
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
    public Account updateAccount(int accountId, Account account) throws Exception {

        try {
            Account existingAccount = findAccountById(accountId);
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
}
