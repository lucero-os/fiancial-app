package com.example.financial_app.services.payment;

import java.time.LocalDate;

import com.example.financial_app.DTOs.Payment.CreatePaymentDTO;
import com.example.financial_app.DTOs.Payment.Stripe.StripePaymentDTO;
import com.example.financial_app.exceptions.CustomException;
import com.example.financial_app.interfaces.payment.iPaymentStrategy;
import com.example.financial_app.models.Payment;
import com.example.financial_app.models.PaymentDetail;
import com.example.financial_app.models.PaymentIntegration;
import com.example.financial_app.services.payment.stripe.StripeService;
import com.example.financial_app.utils.DateUtil;
import com.example.financial_app.utils.ObjectMapperUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PaymentService {
    @PersistenceContext
    private EntityManager entityManager;
    private ObjectMapperUtil objectMapper;

    public PaymentService(ObjectMapperUtil objectMapper) throws Exception{
        this.setObjectMapper(objectMapper);
    }

    private void setObjectMapper(ObjectMapperUtil objectMapper){
        this.objectMapper = objectMapper;
    }

    private iPaymentStrategy getPaymentStrategy(String integrationCode) {
        iPaymentStrategy strategy = null;
        switch(integrationCode){
            case "Stripe":
                strategy = new StripeService(this.objectMapper);
        }

        return strategy;
    }

    public void generatePayment(CreatePaymentDTO createPaymentDTO, Long userSubscriptionId) throws Exception{
        PaymentIntegration integration = this.entityManager.createQuery("SELECT ps FROM PaymentIntegration ps WHERE ps.id = :paymentStrategyId", PaymentIntegration.class)
                                                            .setParameter("paymentStrategyId", createPaymentDTO.getPaymentIntegrationId())
                                                            .getSingleResult();
        if(integration == null) throw new CustomException("Payment method not found");

        StripePaymentDTO paymentDTO = objectMapper.mapEntity(createPaymentDTO, StripePaymentDTO.class);
        iPaymentStrategy paymentStrategy = this.getPaymentStrategy(integration.getCode());

        String paymentReference = paymentStrategy.generatePayment(paymentDTO);

        String lastFourNumbers = paymentDTO.getCardNumber().substring(paymentDTO.getCardNumber().length() - 4);
        PaymentDetail paymentDetail = new PaymentDetail(paymentDTO.getAmount(), paymentReference, lastFourNumbers);
        this.entityManager.persist(paymentDetail);

        LocalDate now = DateUtil.getCurrentDate();
        Payment payment = new Payment(userSubscriptionId, integration, paymentDetail, now);
        this.entityManager.persist(payment);

    }
}
