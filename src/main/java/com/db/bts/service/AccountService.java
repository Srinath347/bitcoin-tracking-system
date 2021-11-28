package com.db.bts.service;

import com.db.bts.entity.Account;

public interface AccountService {

    public Account findAccountById(int accountId) throws Exception;

    public Account addAccount(Account account) throws Exception;

    public Account updateAccount(int accountId, Account account) throws Exception;

    public void deleteAccountById(int accountId) throws Exception;

    public Account findAccountByUserId(int userId) throws Exception;

    public Double findBalanceByUserId(int userId) throws Exception;

    public Double findBitcoinsByUserId(int userId) throws Exception;

    public Account addAmountToUserAccount(int userId, float amount) throws Exception;

}