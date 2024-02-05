package com.example.financial_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financial_app.exceptions.CustomException;
import com.example.financial_app.models.Notification;
import com.example.financial_app.repositories.iNotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private iNotificationRepository notificationRepository;

    public Notification getNotificationById(Long notificationId) throws CustomException{
        Optional<Notification> notificationOptional = this.notificationRepository.findById(notificationId);
        Notification notification = notificationOptional.orElseThrow(() -> new CustomException("Notification not found"));

        return notification;
    }
}