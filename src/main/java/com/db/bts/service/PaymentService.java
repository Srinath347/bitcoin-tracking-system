package com.db.bts.service;

import com.db.bts.entity.Payment;
import com.db.bts.model.PaymentModel;
import com.db.bts.model.PaymentStatusModel;

import java.util.List;

public interface PaymentService {

    public Payment findPaymentById(int paymentId) throws Exception;

    public Payment save(Payment payment) throws Exception;

    public List<PaymentModel> findPaymentsByTraderId(int traderId) throws Exception;

    public Payment updatePaymentStatus(PaymentStatusModel paymentStatusModel) throws Exception;
}
