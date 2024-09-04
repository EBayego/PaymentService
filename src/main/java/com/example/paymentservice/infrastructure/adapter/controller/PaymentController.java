package com.example.paymentservice.infrastructure.adapter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.paymentservice.application.services.PaymentService;
import com.example.paymentservice.domain.model.CreditCard;
import com.example.paymentservice.domain.model.Payment;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> registerPayment(@RequestBody Payment payment) {
    	Payment savedPayment = paymentService.registerPayment(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> listPayments() {
        List<Payment> payments = paymentService.listPayments();
        for (Payment payment : payments) {
            CreditCard card = payment.getCreditCard();
            card.setMaskedNumber(card.maskNumber(card.getNumber()));
        } 
        return ResponseEntity.ok(payments);
    }
}