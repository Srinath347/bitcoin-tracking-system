package com.db.bts.service;

import com.db.bts.entity.Account;
import com.db.bts.entity.Admin;
import com.db.bts.entity.User;

public interface AccountService {

    public Account findAccountById(int accountId) throws Exception;

    public Account addAccount(Account account) throws Exception;

    public Account updateAccount(int accountId, Account account) throws Exception;

    public void deleteAccountById(int accountId) throws Exception;

}