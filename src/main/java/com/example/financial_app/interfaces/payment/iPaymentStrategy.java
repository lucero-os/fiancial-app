package com.example.financial_app.interfaces.payment;

import com.example.financial_app.DTOs.Payment.PaymentDTO;

public interface iPaymentStrategy {
    public String generatePayment(PaymentDTO paymentDTO) throws Exception;
}
