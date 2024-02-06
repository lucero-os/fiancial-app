package com.example.financial_app.services.payment.stripe;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.example.financial_app.DTOs.Payment.PaymentDTO;
import com.example.financial_app.DTOs.Payment.Stripe.StripeChargeDTO;
import com.example.financial_app.DTOs.Payment.Stripe.StripePaymentDTO;
import com.example.financial_app.DTOs.User.UserDTO;
import com.example.financial_app.DTOs.Payment.Stripe.StripePaymentDTO;
import com.example.financial_app.interfaces.payment.iPaymentStrategy;
import com.example.financial_app.utils.ObjectMapperUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;

import jakarta.annotation.PostConstruct;

public class StripeService implements iPaymentStrategy {
    @Value("$api.stripe.key")
    private String apiKey;

    @Value("api.stripe.username")
    private String username;

    private ObjectMapperUtil objectMapper;

    
    public StripeService(ObjectMapperUtil objectMapper){
        this.setObjectMapper(objectMapper);
    }

    @PostConstruct
    public void init(){
        Stripe.apiKey = this.apiKey;
    }

    public void setObjectMapper(ObjectMapperUtil objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String generatePayment(PaymentDTO paymentDTO) throws Exception{
        StripePaymentDTO stripePaymentDTO = objectMapper.mapEntity(paymentDTO, StripePaymentDTO.class);
        String token = this.createCardToken(stripePaymentDTO);
        if(token == null) throw new Exception("Error getting stripe token");

        String message = "Financial app " + paymentDTO.getDetail();
        StripeChargeDTO chargeDTO = new StripeChargeDTO(token, this.username, paymentDTO.getAmount(), message);
        String result = this.charge(chargeDTO);
        if(result == null) throw new Exception("Error generating stripe payment");
        token = result;

        return result;
    }
    
    public String createCardToken(StripePaymentDTO model) {

        try {
            String tokenId = null;
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                tokenId = token.getId();
            }

            return tokenId;
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String charge(StripeChargeDTO chargeDTO) {

        try {
            String paymentReference = null;

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeDTO.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id " + chargeDTO.getMessage());
            chargeParams.put("source", chargeDTO.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("subscriptionBought", chargeDTO.getMessage());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);

            if (charge.getPaid()) paymentReference = charge.getId();

            return paymentReference;
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
