package com.example.financial_app.DTOs.Notification;

public class NotificationTypeDTO {
    private String name;

    public NotificationTypeDTO() {}
    
    public NotificationTypeDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
