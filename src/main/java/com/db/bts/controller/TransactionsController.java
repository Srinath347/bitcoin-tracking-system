package com.db.bts.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.db.bts.controller.api.BtsApiController;
import com.db.bts.entity.Admin;
import com.db.bts.entity.Audit;
import com.db.bts.entity.Transaction;
import com.db.bts.model.TransactionCancelModel;
import com.db.bts.model.TransactionModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.model.TransactionTimeModel;
import com.db.bts.service.impl.AuditServiceImpl;
import com.db.bts.service.impl.TransactionServiceImpl;

import lombok.NonNull;

@RestController
@RequestMapping("/bts/transactions")
public class TransactionsController {

	Logger logger = LoggerFactory.getLogger(BtsApiController.class);
	 
	 
    @Autowired
    private TransactionServiceImpl transactionService;
    
    @Autowired
    private AuditServiceImpl auditService;
    
    @GetMapping("")
    public ModelAndView buyOrSell() throws Exception {
        
        return new ModelAndView("userBuySell");
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
    public ModelAndView addTransaction(@ModelAttribute TransactionModel transactionDTO) throws Exception {
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
    
    @GetMapping("/transactionHistoryOnCancel")
    public ModelAndView redirectToTransactionHistory(HttpSession session, int transactionId) throws Exception {
    	Admin trader = (Admin)session.getAttribute("admin");
    	List<Transaction> transactionList = transactionService.getTransactionByTraderId(trader.getId());
    	session.setAttribute("transactionIdToCancel", transactionId);
    	return new ModelAndView("traderTransactionHistory", "transactionList", transactionList);
    	
    }
    
    @GetMapping("/cancel")
    public ModelAndView cancelTransaction(@RequestParam("userId") int userId, @RequestParam("traderId") int traderId, @RequestParam("transactionId") int transactionId, HttpSession session) throws Exception {
        logger.info("cancellation request");
        TransactionCancelModel transactionCancelModel = new TransactionCancelModel();
        transactionCancelModel.setUserId(userId);
        transactionCancelModel.setTraderId(traderId);
        transactionCancelModel.setTransactionId(transactionId);
        Audit audit = auditService.cancelTransaction(transactionCancelModel);
        logger.info("audit {}", audit);
        return redirectToTransactionHistory(session, transactionId);
    }


}
