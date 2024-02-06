package com.example.financial_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financial_app.DTOs.Subscription.SubscriptionDTO;
import com.example.financial_app.exceptions.CustomException;
import com.example.financial_app.models.Subscription;
import com.example.financial_app.repositories.iSubscriptionRepository;
import com.example.financial_app.utils.ObjectMapperUtil;

@Service
public class SubscriptionService {
    @Autowired
    iSubscriptionRepository subscriptionRepository;
    @Autowired
    private ObjectMapperUtil objectMapper;

    public SubscriptionDTO getSubscriptionById(Long subscriptionId) throws Exception{
        Optional<Subscription> subscriptionOptional = this.subscriptionRepository.findById(subscriptionId);
        Subscription subscription = subscriptionOptional.orElseThrow(() -> new CustomException("Subscription not found"));

        SubscriptionDTO result = objectMapper.mapEntity(subscription, SubscriptionDTO.class);

        return result;
    }

    public List<SubscriptionDTO> getSubscriptions(){
        List<Subscription> subscriptions = this.subscriptionRepository.findAll();
        List<SubscriptionDTO> result = objectMapper.mapEntities(subscriptions, SubscriptionDTO.class);
        return result;
    }

}
