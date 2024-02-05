package com.example.financial_app.DTOs.User;

import java.io.Serializable;
import java.util.Set;

import com.example.financial_app.DTOs.Notification.NotificationDTO;

public class UserDTO implements Serializable{
    private Long id;
    private String name;
    private String surname;
    private String mail;
    Set<NotificationDTO> notifications;
    // Set<SubscriptionDTO> subscriptions;

    public UserDTO(){}
    
    public UserDTO(String name, String surname, String mail, Set<NotificationDTO> notifications) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.notifications = notifications;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Set<NotificationDTO> getNotifications() {
        return notifications;
    }
    public void setNotifications(Set<NotificationDTO> notifications) {
        this.notifications = notifications;
    }
}
