package com.db.bts.service.impl;

import com.db.bts.entity.Payment;
import com.db.bts.enums.PaymentStatus;
import com.db.bts.model.PaymentStatusModel;
import com.db.bts.repository.PaymentRepository;
import com.db.bts.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment findPaymentById(int paymentId) throws Exception {
        return paymentRepository.findById(paymentId).orElseThrow(Exception::new);
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        return Optional.ofNullable(paymentRepository.save(payment))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to add payment details"));
    }

    @Override
    public List<Payment> findPaymentsByTraderId(int traderId) throws Exception {
        logger.info("fetching payments for traderId: {}", traderId);
        return Optional.ofNullable(paymentRepository.findPaymentsByTraderId(traderId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to fetch payment requests"));
    }

    @Override
    public Payment updatePaymentStatus(PaymentStatusModel paymentStatusModel) throws Exception {
        Payment payment = Optional.ofNullable(findPaymentById(paymentStatusModel.getPaymentId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment not found"));
        if(paymentStatusModel.getStatus().equalsIgnoreCase("accepted")) {
            payment.setStatus(PaymentStatus.ACCEPTED.getValue());
        } else {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        }
        return Optional.ofNullable(paymentRepository.save(payment))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to update payment requests"));
    }
}
