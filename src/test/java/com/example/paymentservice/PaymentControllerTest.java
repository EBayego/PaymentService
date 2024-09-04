package com.example.paymentservice;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.paymentservice.application.services.PaymentService;
import com.example.paymentservice.domain.model.CreditCard;
import com.example.paymentservice.domain.model.Payment;
import com.example.paymentservice.infrastructure.adapter.controller.PaymentController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {
	
	private PaymentService paymentService;
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        paymentService = Mockito.mock(PaymentService.class);
        paymentController = new PaymentController(paymentService);
    }

    @Test
    void testRegisterPayment() {
        CreditCard creditCard = new CreditCard("1234567890123456");
        Payment payment = new Payment(1L, creditCard, 100.0, LocalDate.now(), "Test payment");

        when(paymentService.registerPayment(payment)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.registerPayment(payment);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
        verify(paymentService, times(1)).registerPayment(payment);
    }

    @Test
    void testListPayments() {
        CreditCard creditCard = new CreditCard("1234567890123456");
        Payment payment1 = new Payment(2L, creditCard, 100.0, LocalDate.now(), "Payment 1");
        Payment payment2 = new Payment(3L, new CreditCard("9876543210987654"), 200.0, LocalDate.now(), "Payment 2");

        when(paymentService.listPayments()).thenReturn(Arrays.asList(payment1, payment2));

        ResponseEntity<List<Payment>> response = paymentController.listPayments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Payment> payments = response.getBody();
        assertEquals(2, payments.size());
        assertEquals("****-****-****-3456", payments.get(0).getCreditCard().getMaskedNumber());
        assertEquals("****-****-****-7654", payments.get(1).getCreditCard().getMaskedNumber());
        verify(paymentService, times(1)).listPayments();
    }
}
