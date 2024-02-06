package com.example.financial_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financial_app.DTOs.Payment.PaymentDTO;
import com.example.financial_app.DTOs.Payment.Stripe.StripePaymentDTO;
import com.example.financial_app.DTOs.User.CreateUserDTO;
import com.example.financial_app.DTOs.User.CreateUserSubscriptionDTO;
import com.example.financial_app.DTOs.User.UserDTO;
import com.example.financial_app.exceptions.CustomException;
import com.example.financial_app.services.UserService;
import com.example.financial_app.utils.ResponseUtil;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired 
    UserService userService;

    @PostMapping("/create")
    public Map<String, Object> createUser(@RequestBody CreateUserDTO user) throws CustomException{
        Map<String, Object> response = new HashMap<>();

        this.userService.createUser(user);

        response.put("message", "User created succesfully");
        
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseUtil.ApiResponse<Object>> getUsers(){
        Map<String, Object> data = new HashMap<>();
        
        List<UserDTO> users = this.userService.getUsers();
        data.put("users", users);
        
        return ResponseUtil.success(data);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUtil.ApiResponse<Object>> getUser(@PathVariable Long userId) throws CustomException{
        Map<String, Object> data = new HashMap<>();

        UserDTO user = this.userService.getUserById(userId);
        data.put("user", user);

        return ResponseUtil.success(data);
    }

    @DeleteMapping("/{userId}")
    public Map<String, Object> deleteUser(@PathVariable Long userId) throws CustomException{
        Map<String, Object> response = new HashMap<>();
        
        this.userService.deleteUser(userId);
        response.put("message", "User deleted succesfully");
        
        return response;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<ResponseUtil.ApiResponse<Object>> newUserSubscription(CreateUserSubscriptionDTO userSubscriptionDTO) throws Exception{
        Map<String, Object> data = new HashMap<>();

        this.userService.newUserSubscription(userSubscriptionDTO);

        return ResponseUtil.success(data);
    }
}
