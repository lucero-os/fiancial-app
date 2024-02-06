package com.example.financial_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financial_app.models.UserSubscription;

public interface iUserSubscriptionRepository extends JpaRepository<UserSubscription, Long>{
    
}
