package com.example.financial_app.DTOs.Payment.Stripe;

import com.example.financial_app.DTOs.Payment.PaymentDTO;

public class StripePaymentDTO extends PaymentDTO{
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getExpMonth() {
        return expMonth;
    }
    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }
    public String getExpYear() {
        return expYear;
    }
    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }
    public String getCvc() {
        return cvc;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
