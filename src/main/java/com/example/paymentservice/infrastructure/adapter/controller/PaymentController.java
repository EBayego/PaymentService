package com.example.paymentservice.infrastructure.adapter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.paymentservice.application.services.PaymentService;
import com.example.paymentservice.domain.model.Payment;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment registerPayment(@RequestBody Payment payment) {
        return paymentService.registerPayment(payment);
    }

    @GetMapping
    public List<Payment> listPayments() {
        return paymentService.listPayments();
    }
}