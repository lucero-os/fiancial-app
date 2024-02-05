package com.example.financial_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financial_app.models.User;

public interface iUserRepository extends JpaRepository<User, Long> {
    User findByMail(String mail);
}
