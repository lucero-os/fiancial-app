package com.example.financial_app.DTOs.User;

import com.example.financial_app.DTOs.Payment.CreatePaymentDTO;

public class CreateUserSubscriptionDTO {
    private CreatePaymentDTO paymentDTO;
    private Long subscriptionId;
    private Long userId;
    
    public CreateUserSubscriptionDTO(CreatePaymentDTO paymentDTO, Long subscriptionId, Long userId) {
        this.paymentDTO = paymentDTO;
        this.subscriptionId = subscriptionId;
        this.userId = userId;
    }

    public CreatePaymentDTO getCreatePaymentDTO() {
        return paymentDTO;
    }
    public void setCreatePaymentDTO(CreatePaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }
    public Long getSubscriptionId() {
        return subscriptionId;
    }
    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
