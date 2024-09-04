package com.example.paymentservice.infrastructure.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.paymentservice.domain.model.Payment;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {
}
