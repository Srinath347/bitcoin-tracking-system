package com.db.bts.service.impl;

import com.db.bts.entity.Payment;

import com.db.bts.repository.PaymentRepository;
import com.db.bts.service.PaymentService;
import com.db.bts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment findPaymentById(int paymentId) throws Exception {
        return paymentRepository.findById(paymentId).orElseThrow(Exception::new);
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        return paymentRepository.save(payment);
    }
}
