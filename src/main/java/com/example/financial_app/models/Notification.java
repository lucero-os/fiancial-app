package com.example.financial_app.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notification_type_id", nullable = false)
    private NotificationType type;

    @ManyToMany(mappedBy = "notifications", fetch = FetchType.LAZY)
    private Set<User> users;
    
    private String name;
    private String description;
    public Long getId() {
        return id;
    }
    private void setId(Long id) {
        this.id = id;
    }
    public NotificationType getType() {
        return type;
    }
    private void setType(NotificationType type) {
        this.type = type;
    }
    public Set<User> getUsers() {
        return users;
    }
    private void setUsers(Set<User> users) {
        this.users = users;
    }
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    
}
