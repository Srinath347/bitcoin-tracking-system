package com.db.bts.controller;

import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.model.TransactionTimeModel;
import com.db.bts.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bts/transactions")
public class TransactionsController {

    @Autowired
    private TransactionServiceImpl transactionService;
    
    @GetMapping("")
    public ModelAndView buyOrSell(HttpSession session) throws Exception {
        User user=(User)session.getAttribute("user");
        TransactionModel transactionModel=new TransactionModel();
        transactionModel.setUserId(user.getId());
        return new ModelAndView("userBuySell","transaction", transactionModel);
    }

    @GetMapping("/transactionHistory")
    public ModelAndView viewTransactionHistoryForAdmin() throws Exception {
        
        return new ModelAndView("traderTransactionHistory");
    }

    

    @GetMapping("/{id}")
    public ModelAndView getTransactionByUserId(@PathVariable(value = "id") int userId) throws Exception {
        List<Transaction> transactionList = transactionService.getTransactionByUserId(userId);
        return new ModelAndView("userTransactionHistory", "transactionList", transactionList);
    }

    @GetMapping("/trader/{id}")
    public ModelAndView getTransactionByTraderId(@PathVariable(value = "id") int traderId) throws Exception {
        List<Transaction> transactionList = transactionService.getTransactionByTraderId(traderId);
        return new ModelAndView("traderTransactionHistory", "transactionList", transactionList);
    }


    @PostMapping("/transaction")
    public ModelAndView addTransaction(@ModelAttribute("transaction") TransactionModel transactionDTO) throws Exception {
        Transaction transaction = transactionService.addTransaction(transactionDTO);
        List<Transaction> transactionList = transactionService.getTransactionByUserId(transaction.getUser().getId());
        return new ModelAndView("userTransactionHistory", "transactionList", transactionList);
    }
    
    @PostMapping("/traderTransaction")
    public ModelAndView addTransactionTrader(@ModelAttribute TransactionModel transactionDTO) throws Exception {
        Transaction transaction = transactionService.addTransaction(transactionDTO);
        List<Transaction> transactionList = transactionService.getTransactionByTraderId(transaction.getTrader().getId());
        return new ModelAndView("traderTransactionHistory", "transactionList", transactionList);
    }
//        return new ModelAndView();

	/*
	 * @PostMapping("") public ResponseEntity<Transaction>
	 * addTransaction(@RequestBody @NonNull TransactionModel transactionDTO) throws
	 * Exception { Transaction transaction1 =
	 * transactionService.addTransaction(transactionDTO); return
	 * ResponseEntity.ok().body(transaction1);
	 * 
	 * }
	 */

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable(value = "id") int transactionId) throws Exception {
        transactionService.updateStatus(transactionId, 2);
        return ResponseEntity.ok().body("Updated Successfully");
    }

    @GetMapping("/search")
    public ModelAndView findTransactionByCriteria(@RequestParam("value") String value, @RequestParam("field") String field) throws Exception {
        List<TransactionSearchModel> transactions = transactionService.findTransactionByCriteria(value, field);
        return new ModelAndView("traderTransactionHistory", "transactionList", transactions);
    }

//    @GetMapping("/time")
//    public ResponseEntity<TransactionTimeModel> findTransactionsStatisticsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) throws Exception {
//        TransactionTimeModel transactions = transactionService.findTransactionsStatisticsByDate(from, to);
//        return ResponseEntity.ok().body(transactions);
//    }

    @GetMapping("/time")
    public ModelAndView findTransactionsStatisticsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) throws Exception {
        TransactionTimeModel transactions = transactionService.findTransactionsStatisticsByDate(from, to);
        System.out.println("Transactions -->" + transactions);
        return new ModelAndView("manager","transactionStatistics", transactions);
    }

}
