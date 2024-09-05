package com.example.paymentservice.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.paymentservice.domain.model.Payment;
import com.example.paymentservice.infrastructure.exception.PaymentDomainException;
import com.example.paymentservice.application.port.PaymentRepository;

@Service
public class PaymentService {
	private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment registerPayment(Payment payment) {
        validatePayment(payment);
        return paymentRepository.save(payment);
    }

    public List<Payment> listPayments() {
        return paymentRepository.findAll();
    }

    private void validatePayment(Payment payment) {
        if (payment.getAmount() > 100000) {
            throw new PaymentDomainException("Amount exceeds allowed limit of 100,000");
        }

        if (payment.getCreditCard().getNumber().length() != 16) {
            throw new PaymentDomainException("Invalid credit card number");
        }
    }
}
