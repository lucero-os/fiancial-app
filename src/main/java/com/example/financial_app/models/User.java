package com.example.financial_app.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User(Long id, String name, String surname, String mail, String password, Set<Notification> subscriptions) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setMail(mail);
        this.setPassword(password);
        this.setSubscriptions(subscriptions);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String mail;
    private String password;

    @ManyToMany
    @JoinTable(
        name = "user_notifications",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private Set<Notification> notifications;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    private void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMail() {
        return mail;
    }
    private void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    private void setPassword(String password) {
        this.password = password;
    }
    public Set<Notification> getNotification() {
        return notifications;
    }
    private void setSubscriptions(Set<Notification> notifications) {
        if(notifications.size() > 0) this.notifications = notifications;
    }
}
