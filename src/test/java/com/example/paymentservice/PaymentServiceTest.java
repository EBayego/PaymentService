package com.example.paymentservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.paymentservice.application.port.PaymentRepository;
import com.example.paymentservice.application.services.PaymentService;
import com.example.paymentservice.domain.model.CreditCard;
import com.example.paymentservice.domain.model.Payment;
import com.example.paymentservice.infrastructure.exception.PaymentDomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;;

@SpringBootTest
@TestPropertySource(properties = "DATABASE_URL=jdbc:postgresql://localhost:5432/payment_db")
class PaymentServiceTest {

	private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        paymentService = new PaymentService(paymentRepository);
    }
    
    @Test
    void testRegisterPayment() {
        CreditCard creditCard = new CreditCard("1234567890123456");
        Payment payment = new Payment(1L, creditCard, 100.0, LocalDate.now(), "Test payment valid");

        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.registerPayment(payment);
        assertEquals(payment, result);
        verify(paymentRepository, times(1)).save(payment);
        
        Payment payment2 = new Payment(2L, creditCard, 150000.00, LocalDate.now(), "Test payment invalid amount");

        assertThrows(PaymentDomainException.class, () -> {
            paymentService.registerPayment(payment2);
        });
        
        CreditCard creditCard3 = new CreditCard("1234567890");
        Payment payment3 = new Payment(3L, creditCard3, 1000.00, LocalDate.now(), "Test payment invalid cd number");

        assertThrows(PaymentDomainException.class, () -> {
            paymentService.registerPayment(payment3);
        });
    }

    @Test
    void testListPayments() {
        CreditCard creditCard = new CreditCard("1234567890123456");
        Payment payment1 = new Payment(2L, creditCard, 100.0, LocalDate.now(), "Payment 1");
        Payment payment2 = new Payment(3L, new CreditCard("9876543210987654"), 200.0, LocalDate.now(), "Payment 2");

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        List<Payment> payments = paymentService.listPayments();
        assertEquals(2, payments.size());
        assertEquals("1234567890123456", payments.get(0).getCreditCard().getNumber());
        assertEquals("9876543210987654", payments.get(1).getCreditCard().getNumber());
        verify(paymentRepository, times(1)).findAll();
    }
}