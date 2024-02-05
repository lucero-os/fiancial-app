package com.example.financial_app.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedEntityGraph(
    name = "user-with-details",
    attributeNodes = {
        @NamedAttributeNode(value = "notifications"),
        @NamedAttributeNode(value = "subscriptions")
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String mail;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_notifications",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private Set<Notification> notifications;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_subscriptions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private Set<Subscription> subscriptions;

    public User() {}

    public User(String name, String surname, String mail, String password) {
        this.setName(name);
        this.setSurname(surname);
        this.setMail(mail);
        this.setPassword(password);
        this.setNotifications();
        this.setSubscriptions();
    }

    public Long getId() {
        return id;
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
    public Set<Notification> getNotifications() {
        return notifications;
    }
    private void setNotifications() {
        this.notifications = new HashSet<>();
    }
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }
    private void setSubscriptions() {
        this.subscriptions = new HashSet<>();
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", mail=" + mail + ", password="
                + password + ", notifications=" + notifications + "]";
    }
}
