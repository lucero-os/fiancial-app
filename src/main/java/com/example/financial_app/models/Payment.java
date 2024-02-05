package com.example.financial_app.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_subscription_id")
    private Long userSubscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_integration_id")
    private PaymentIntegration paymentIntegration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_details_id")
    private PaymentDetails paymentDetails;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    public Payment(){}
    
    public Payment(Long userSubscriptionId, PaymentIntegration paymentIntegration,
            PaymentDetails paymentDetails, LocalDate paymentDate) {
        this.setUserSubscriptionId(userSubscriptionId);
        this.setPaymentIntegration(paymentIntegration);
        this.setPaymentDetails(paymentDetails);
        this.setPaymentDate(paymentDate);
    }

    public Long getId() {
        return id;
    }

    public Long getUserSubscriptionId() {
        return userSubscriptionId;
    }

    private void setUserSubscriptionId(Long userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }

    public PaymentIntegration getPaymentIntegration() {
        return paymentIntegration;
    }

    private void setPaymentIntegration(PaymentIntegration paymentIntegration) {
        this.paymentIntegration = paymentIntegration;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    private void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    private void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}

