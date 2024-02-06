package com.example.financial_app.DTOs.Payment;

import java.math.BigDecimal;

public class CreatePaymentDTO {
        private BigDecimal amount;
        private String currency;
        private Long paymentIntegrationId;
        
        
        public BigDecimal getAmount() {
            return amount;
        }
        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
        public String getCurrency() {
            return currency;
        }
        public void setCurrency(String currency) {
            this.currency = currency;
        }   
        public Long getPaymentIntegrationId() {
            return paymentIntegrationId;
        }
        public void setPaymentIntegrationId(Long paymentIntegrationId) {
            this.paymentIntegrationId = paymentIntegrationId;
        }     
}
