package com.db.bts.service.impl;

import com.db.bts.entity.Account;
import com.db.bts.repository.AccountRepository;
import com.db.bts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountById(int accountId) throws Exception {
        return accountRepository.findById(accountId).orElseThrow(Exception::new);
    }

    @Override
    public Account save(Account account) throws Exception {
        return accountRepository.save(account);
    }
}
