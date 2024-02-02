package com.example.financial_app.DTOs;

import com.example.financial_app.models.NotificationType;

public class NotificationDTO {
    public Long id;
    public NotificationType type;
    public String name;
    public String description;

    public NotificationDTO(){}
    
    public NotificationDTO(Long id, NotificationType type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
