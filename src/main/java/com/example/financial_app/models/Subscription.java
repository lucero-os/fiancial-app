package com.example.financial_app.models;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer duration;
    private BigDecimal price;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.LAZY)
    private Set<User> users;

    public Subscription(Long id, Integer duration, BigDecimal price) {
        this.setDuration(duration);
        this.setPrice(price);
    }
    public Long getId() {
        return id;
    }
    public Integer getDuration() {
        return duration;
    }
    private void setDuration(Integer duration) {
        this.duration = duration;
    }
    public BigDecimal getPrice() {
        return price;
    }
    private void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
