package com.example.financial_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financial_app.models.PaymentIntegration;

public interface iPaymentIntegrationRepository extends JpaRepository<PaymentIntegration, Long> {

}