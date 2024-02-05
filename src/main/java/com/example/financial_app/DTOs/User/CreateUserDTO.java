package com.example.financial_app.DTOs.User;

import java.util.List;

public class CreateUserDTO {
    private String name;
    private String surname;
    private String mail;
    private String password;
    List<Long> notificationIds;

    public CreateUserDTO(){}
    
    public CreateUserDTO(String name, String surname, String mail, String password, List<Long> notificationIds) {
        this.setName(name);
        this.setSurname(surname);
        this.setMail(mail);
        this.setPassword(password);
        this.setNotificationIds(notificationIds);
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Long> getNotificationIds() {
        return notificationIds;
    }
    public void setNotificationIds(List<Long> notificationIds) {
        this.notificationIds = notificationIds;
    }

    @Override
    public String toString() {
        return "CreateUserWithNotificationDTO [name=" + name + ", surname=" + surname + ", mail=" + mail + ", password="
                + password + ", notificationIds=" + notificationIds + "]";
    }
}

