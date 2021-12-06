package com.db.bts.service.impl;

import com.db.bts.entity.*;
import com.db.bts.enums.TransactionStatus;
import com.db.bts.model.TransactionCancelModel;
import com.db.bts.repository.AuditRepository;
import com.db.bts.service.AccountService;
import com.db.bts.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Autowired
    private AuditService auditService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public Audit cancelTransaction(TransactionCancelModel transactionCancelModel) throws Exception {
        Transaction transaction = transactionService.findTransactionById(transactionCancelModel.getTransactionId());
        User user = userService.findUserById(transactionCancelModel.getUserId());
        Account account = accountService.findAccountByUserId(user.getId());
        Admin admin = adminService.findAdminById(transactionCancelModel.getTraderId());
        if(user.getTrader().getId() != admin.getId()) {
            throw new Exception("Trader is unauthorized to cancel this transaction");
        }
        transaction = updateTrsnactionStatus(transaction);
        updateAccountBalance(account, transaction);
        String message = buildMessage(admin, user, transaction);
        Audit audit = new Audit();
        audit.setMessage(message);
        audit.setTrader(admin);
        audit.setUser(user);
        audit.setTransaction(transaction);
        logger.info("audit {}", audit);
        Audit savedAudit = auditRepository.save(audit);
        return savedAudit;
    }

    private Transaction updateTrsnactionStatus(Transaction transaction) throws Exception {
        if (TransactionStatus.CANCELLED.getValue() == transaction.getStatus()) {
            throw new Exception("transaction is already cancelled");
        }
        transaction.setStatus(TransactionStatus.CANCELLED.getValue());
        transactionService.updateStatus(transaction.getId(), transaction.getStatus());
        return transaction;
    }

    private Account updateAccountBalance(Account account, Transaction transaction) throws Exception {
        Float balance = account.getBalance();
        Float userBitcoin = account.getBitcoin();
        float transactionAmount = transaction.getAmount();
        float commissionValue = transaction.getCommissionValue();
        Float bitcoin = transaction.getBitcoin();
        if (transaction.getType().equalsIgnoreCase("BUY")) {
            balance += transactionAmount;
            balance += commissionValue;
            userBitcoin -= bitcoin;
        } else {
            balance -= transactionAmount;
            balance -= commissionValue;
            userBitcoin += bitcoin;
        }
        account.setBalance(balance);
        account.setBitcoin(userBitcoin);
        logger.info("existing balance {}", account);
        Account account1 = accountService.updateAccount(account.getUser().getId(), account);
        logger.info("updated account  {}", account1);
        return  account1;
    }

    private String buildMessage(Admin admin, User user, Transaction transaction) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Trader with id: ").append(admin.getId()).append(" name: ");
        stringBuilder.append(admin.getFirstName());
        stringBuilder.append(" Cancelled transaction with id : ").append(transaction.getId());
        stringBuilder.append(" of user with id: ").append(user.getId()).append(" name: ").append(user.getFirstName());
        return stringBuilder.toString();
    }
}
