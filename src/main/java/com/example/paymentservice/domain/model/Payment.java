package com.example.paymentservice.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Payment {
	@Id
	@NotNull(message = "Credit card number cannot be null")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Embedded
    private CreditCard creditCard;
	
	@NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;
	
	@PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;
	
    private String description;
    
	public Payment(Long id, CreditCard creditCard, Double amount, LocalDate paymentDate, String description) {
		super();
		this.id = id;
		this.creditCard = creditCard;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.description = description;
	}
	
	public Payment() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

    
}