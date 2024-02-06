package com.example.financial_app.DTOs.Payment.Stripe;

import java.math.BigDecimal;

public class StripeChargeDTO {

	private String  stripeToken;
	private String  username;
	private Double  amount;
	private String  message;
    
    public StripeChargeDTO(String stripeToken, String username, BigDecimal amount, String message) {
        this.setStripeToken(stripeToken);
        this.setUsername(username);
        this.setAmount(amount);
        this.setMessage(message);
    }
    public String getStripeToken() {
        return stripeToken;
    }
    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount.doubleValue();
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}


