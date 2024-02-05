package com.example.financial_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financial_app.models.Notification;

public interface iNotificationRepository extends JpaRepository<Notification, Long> {

}