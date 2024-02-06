package com.example.financial_app.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financial_app.exceptions.CustomException;
import com.example.financial_app.models.Subscription;
import com.example.financial_app.models.User;
import com.example.financial_app.models.UserSubscription;
import com.example.financial_app.repositories.iSubscriptionRepository;
import com.example.financial_app.repositories.iUserRepository;
import com.example.financial_app.repositories.iUserSubscriptionRepository;
import com.example.financial_app.utils.DateUtil;

@Service
public class UserSubscriptionService {
    @Autowired
    private iUserRepository userRepository;
    @Autowired
    private iSubscriptionRepository subscriptionRepository;
    @Autowired
    private iUserSubscriptionRepository userSubscriptionRepository;

    public Long createUserSubscription(Long userId, Long subscriptionId) throws Exception{
        Optional<User> userOptional = this.userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new CustomException("User not found"));
        Optional<Subscription> subscriptionOptional = this.subscriptionRepository.findById(subscriptionId);
        Subscription subscription = subscriptionOptional.orElseThrow(() -> new CustomException("Subscription not found"));
        
        LocalDate startDate = DateUtil.getCurrentDate();
        LocalDate endDate = startDate.plusMonths(subscription.getDuration());

        UserSubscription userSubscription = new UserSubscription(user, subscription, DateUtil.toDate(startDate), DateUtil.toDate(endDate));
        this.userSubscriptionRepository.save(userSubscription);

        return userSubscription.getId();
    }
}
