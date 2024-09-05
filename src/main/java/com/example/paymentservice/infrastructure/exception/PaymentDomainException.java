package com.example.paymentservice.infrastructure.exception;

public class PaymentDomainException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -713911512075449720L;

	public PaymentDomainException(String message) {
        super(message);
    }
}