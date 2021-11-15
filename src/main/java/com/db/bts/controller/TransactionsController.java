package com.db.bts.controller;

import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.service.TransactionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bts/transactions")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<Transaction> getTransactionByUserId(@PathVariable(value = "id") int userId) throws Exception {
        Transaction transaction = transactionService.findTransactionById(userId);
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping()
    public ResponseEntity<Transaction> createNewTransaction(@RequestBody @NonNull Transaction transaction) throws Exception {
        Transaction transaction1 = transactionService.save(transaction);
        return ResponseEntity.ok().body(transaction1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable(value = "id") int transactionId) throws Exception {
        transactionService.updateStatus(transactionId, 2);
        return ResponseEntity.ok().body("Updated Successfully");
    }

}
