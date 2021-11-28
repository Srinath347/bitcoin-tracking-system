package com.db.bts.controller;

import com.db.bts.entity.Account;
import com.db.bts.entity.Admin;
import com.db.bts.entity.User;
import com.db.bts.service.impl.AccountServiceImpl;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bts")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/account/{id}")
    public ModelAndView getAccountById(@PathVariable(value = "id") int accountId) throws Exception {
        logger.info("GET request for account with id {}", accountId);
        Account account = accountService.findAccountById(accountId);
        logger.info("account details : {}", account);
        return new ModelAndView("wallet","account", account);
    }

    @PostMapping("/account")
    public ResponseEntity<Account> addAccount(@RequestBody @NonNull Account account) throws Exception {
        logger.info("POST request for account with details: {}", account);
        Account savedAccount = accountService.addAccount(account);
        logger.info("Account with id: [{}] signed up successfully", savedAccount.getId());
        return ResponseEntity.ok().body(savedAccount);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") int accountId,
                                           @RequestBody @NonNull Account account) throws Exception {
        logger.info("PUT request for account with id: [{}]", accountId);
        Account updatedAccount = accountService.updateAccount(accountId, account);
        logger.info("Account details updated successfully");
        return ResponseEntity.ok().body(updatedAccount);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int accountId) throws Exception {
        try {
            logger.info("DELETE request for account with id: [{}]", accountId);
            accountService.deleteAccountById(accountId);
            logger.info("account with id: [{}] deleted successfully", accountId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete account");
        }
    }

    @PutMapping("/wallet/add")
    public ModelAndView depositMoney(@ModelAttribute("userId") int userId,
                                     @ModelAttribute("amount") float amount) throws Exception {
        logger.info("update balance request for userId : {}, amount: {}", userId, amount);
        Account account = accountService.addAmountToUserAccount(userId, amount);
        logger.info("updated account: {}", account);
        return new ModelAndView("wallet");
    }
}
