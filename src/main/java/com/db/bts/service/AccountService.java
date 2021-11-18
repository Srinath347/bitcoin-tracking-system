package com.db.bts.service;


import com.db.bts.entity.Account;
import com.db.bts.entity.User;

public interface AccountService {

    public Account findAccountById(int accountId) throws Exception;

    public Account save(Account account) throws Exception;
}