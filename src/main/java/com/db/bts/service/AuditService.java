package com.db.bts.service;

import com.db.bts.entity.Audit;
import com.db.bts.model.TransactionCancelModel;

public interface AuditService {

    public Audit cancelTransaction(TransactionCancelModel transactionCancelModel) throws Exception;
}
