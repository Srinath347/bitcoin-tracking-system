package com.db.bts.controller;

import com.db.bts.entity.Transaction;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.model.TransactionTimeModel;
import com.db.bts.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bts/transactions")
public class TransactionsController {

    @Autowired
    private TransactionServiceImpl transactionService;
    
    @GetMapping("")
    public ModelAndView buyOrSell() throws Exception {
        
        return new ModelAndView("userBuySell");
    }


    @GetMapping("/{id}")
    public ModelAndView getTransactionByUserId(@PathVariable(value = "id") int userId) throws Exception {
        List<Transaction> transactionList = transactionService.getTransactionByUserId(userId);
        return new ModelAndView("userTransactionHistory", "transactionList", transactionList);
    }

    @PostMapping("/transaction")
    public void addTransaction(@RequestBody TransactionModel transactionDTO) throws Exception {
        Transaction transaction = transactionService.addTransaction(transactionDTO);
//        return new ModelAndView();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable(value = "id") int transactionId) throws Exception {
        transactionService.updateStatus(transactionId, 2);
        return ResponseEntity.ok().body("Updated Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<TransactionSearchModel>> findTransactionByCriteria(@RequestParam("value") String value, @RequestParam("field") String field) throws Exception {
        List<TransactionSearchModel> transactions = transactionService.findTransactionByCriteria(value, field);
        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping("/time")
    public ResponseEntity<TransactionTimeModel> findTransactionsStatisticsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) throws Exception {
        TransactionTimeModel transactions = transactionService.findTransactionsStatisticsByDate(from, to);
        return ResponseEntity.ok().body(transactions);
    }

}
