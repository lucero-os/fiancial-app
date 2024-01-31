package com.example.financial_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financial_app.DTOs.UserDTO;
import com.example.financial_app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired 
    UserService userService;

    @GetMapping("/all")
    public Map<String, Object> getUsers(){
        Map<String, Object> response = new HashMap<>();
        List<UserDTO> users = this.userService.getUsers();
        response.put("users", users);
        return response;
    }

    @PostMapping("/create")
    public Map<String, Object> createUser(@RequestBody UserDTO user){
        Map<String, Object> response = new HashMap<>();
        this.userService.createUser(user);
        response.put("message", "User created succesfully");
        return response;
    }
}
