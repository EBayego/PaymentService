package com.example.paymentservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.paymentservice.application.port.PaymentRepository;
import com.example.paymentservice.application.services.PaymentService;
import com.example.paymentservice.domain.model.Payment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceTest {
	
	@MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @Test
    void testRegisterPayment() {
        Payment payment = new Payment();
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.registerPayment(payment);

        assertEquals(payment, result);
    }

    @Test
    void testListPayments() {
        Payment payment = new Payment();
        when(paymentRepository.findAll()).thenReturn(List.of(payment));

        List<Payment> payments = paymentService.listPayments();

        assertEquals(1, payments.size());
    }
}