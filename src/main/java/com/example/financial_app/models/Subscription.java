package com.example.financial_app.models;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer duration;
    private BigDecimal price;
    
}
