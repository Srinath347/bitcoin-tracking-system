package com.db.bts.service;

import com.db.bts.entity.Payment;

import java.util.List;

public interface PaymentService {

    public Payment findPaymentById(int paymentId) throws Exception;

    public Payment save(Payment payment) throws Exception;

    public List<Payment> findPaymentsByTraderId(int traderId) throws Exception;

    public Payment updatePaymentStatus(int paymentId, String status) throws Exception;
}
