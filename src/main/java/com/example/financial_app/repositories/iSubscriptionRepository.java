package com.example.financial_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financial_app.models.Subscription;

public interface iSubscriptionRepository extends JpaRepository<Subscription, Long> {
    
}
