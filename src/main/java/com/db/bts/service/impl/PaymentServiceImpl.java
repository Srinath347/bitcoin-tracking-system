package com.db.bts.service.impl;

import com.db.bts.entity.Payment;
import com.db.bts.enums.PaymentStatus;
import com.db.bts.model.PaymentStatusModel;
import com.db.bts.repository.PaymentRepository;
import com.db.bts.service.PaymentService;
import com.db.bts.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserService userService;

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
        List<Payment> paymentList = new ArrayList<>();
        logger.info("fetching payments for traderId: {}", traderId);
        List<Payment> payments = Optional.ofNullable(paymentRepository.findPaymentsByTraderId(traderId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to fetch payment requests"));
        logger.info("Payments: {}", payments.size());
        if(payments.size() > 0) {
            for(Payment payment : payments) {
                Payment payment1 = new Payment();
                payment1.setUser(payment.getUser());
                payment1.setTrader(payment.getTrader());
                payment1.setAmount(payment.getAmount());
                payment1.setStatus(payment.getStatus());
                payment1.setPid(payment.getPid());
                payment1.setTime(payment.getTime());
                paymentList.add(payment1);
//                PaymentModel paymentModel = new PaymentModel();
//                paymentModel.setPid(payment.getPid());
//                paymentModel.setAmount(payment.getAmount());
//                paymentModel.setStatus(payment.getStatus());
//                paymentModel.setTraderId(payment.getTrader().getId());
//                paymentModel.setTime(payment.getTime());
//                paymentModel.setUserName(payment.getUser().getFirstName() + " " + payment.getUser().getLastName());
//                paymentModels.add(paymentModel);
            }
        }
        return payments;
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
