package com.example.paymentservice.infrastructure.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.paymentservice.application.port.PaymentRepository;
import com.example.paymentservice.domain.model.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    
    private final JpaPaymentRepository jpaPaymentRepository;

    public PaymentRepositoryImpl(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return jpaPaymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return jpaPaymentRepository.findAll();
    }
}
