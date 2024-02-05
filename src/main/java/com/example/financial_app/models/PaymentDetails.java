package com.example.financial_app.models;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_details")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Column(name = "payment_external_reference")
    private String paymentExternalReference;

    @Column(name = "card_last_four_numbers")
    private Integer cardLastFourNumbers;

    public PaymentDetails(){} 
    
    public PaymentDetails(BigDecimal amount, String paymentExternalReference, Integer cardLastFourNumbers) {
        this.setAmount(amount);
        this.setPaymentExternalReference(paymentExternalReference);
        this.setCardLastFourNumbers(cardLastFourNumbers);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentExternalReference() {
        return paymentExternalReference;
    }

    private void setPaymentExternalReference(String paymentExternalReference) {
        this.paymentExternalReference = paymentExternalReference;
    }

    public Integer getCardLastFourNumbers() {
        return cardLastFourNumbers;
    }

    private void setCardLastFourNumbers(Integer cardLastFourNumbers) {
        this.cardLastFourNumbers = cardLastFourNumbers;
    }

}

