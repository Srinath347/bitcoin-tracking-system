package com.db.bts.controller;

import com.db.bts.entity.Account;
import com.db.bts.service.impl.AccountServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") int accountId) throws Exception {

        Account account = accountService.findAccountById(accountId);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> save(@RequestBody @NonNull Account account) throws Exception {
        Account account1 = accountService.save(account);
        return ResponseEntity.ok().body(account1);
    }
}
