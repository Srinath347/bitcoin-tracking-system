package com.db.bts.controller;

import com.db.bts.entity.User;
import com.db.bts.entity.Payment;
import com.db.bts.model.PaymentModel;
import com.db.bts.service.impl.PaymentServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bts")
public class PaymentsController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @GetMapping("/payment/{pid}")
    public ResponseEntity<Payment> getUserById(@PathVariable(value = "pid") int paymentId) throws Exception {
        Payment payment = paymentService.findPaymentById(paymentId);
        return ResponseEntity.ok().body(payment);
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> save(@RequestBody @NonNull Payment payment) throws Exception {
        Payment payment1 = paymentService.save(payment);
        return ResponseEntity.ok().body(payment1);
    }
}
