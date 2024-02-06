package com.example.financial_app.DTOs.Payment;

import java.math.BigDecimal;

public abstract class PaymentDTO {
        private BigDecimal amount;
        private String currency;
        private String detail;
        
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
        public String getDetail() {
            return detail;
        }
        public void setDetail(String detail) {
            this.detail = detail;
        }
        
}
