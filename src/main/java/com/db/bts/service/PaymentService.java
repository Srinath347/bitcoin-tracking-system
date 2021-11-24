package com.db.bts.service;

import com.db.bts.entity.Payment;

public interface PaymentService {

    public Payment findPaymentById(int paymentId) throws Exception;

    public Payment save(Payment payment) throws Exception;
}
