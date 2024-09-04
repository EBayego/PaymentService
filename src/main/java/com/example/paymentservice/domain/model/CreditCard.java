package com.example.paymentservice.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CreditCard {
    private String number;
    private String maskedNumber;

    public CreditCard() {
    }

    public CreditCard(String number) {
        this.number = number;
        this.maskedNumber = maskNumber(number);
    }

    public String maskNumber(String number) {
        return "****-****-****-" + number.substring(number.length() - 4);
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMaskedNumber() {
		return maskedNumber;
	}

	public void setMaskedNumber(String maskedNumber) {
		this.maskedNumber = maskedNumber;
	}

   
}