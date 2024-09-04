package com.example.paymentservice.application.port;

import java.util.List;

import com.example.paymentservice.domain.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
    List<Payment> findAll();
}
