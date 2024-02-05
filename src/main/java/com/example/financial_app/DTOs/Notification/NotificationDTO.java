package com.example.financial_app.DTOs.Notification;

public class NotificationDTO {
    public NotificationTypeDTO type;
    public String name;
    public String description;

    public NotificationDTO(){}
    
    public NotificationDTO(NotificationTypeDTO type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }
    public NotificationTypeDTO getType() {
        return type;
    }
    public void setType(NotificationTypeDTO type) {
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
