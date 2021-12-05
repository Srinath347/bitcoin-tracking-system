package com.db.bts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.db.bts.entity.Payment;
import com.db.bts.service.impl.PaymentServiceImpl;

import lombok.NonNull;

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


    @GetMapping("/payments/trader/{id}")
    public ModelAndView findPaymentsByTraderId(@PathVariable(value = "id") int traderId) throws Exception {
        List<Payment> paymentModels = paymentService.findPaymentsByTraderId(traderId);
        return new ModelAndView("pendingPayments","paymentList", paymentModels);

    }

    @GetMapping("/payment/status")
    public ModelAndView updatePaymentStatus(@RequestParam("paymentId") int paymentId, @RequestParam("status") String status, @RequestParam("traderId") int traderId) throws Exception {
        Payment payment = paymentService.updatePaymentStatus(paymentId, status);
        List<Payment> paymentModels = paymentService.findPaymentsByTraderId(traderId);
        return new ModelAndView("pendingPayments","paymentList", paymentModels);

    }

    

    
    
}
