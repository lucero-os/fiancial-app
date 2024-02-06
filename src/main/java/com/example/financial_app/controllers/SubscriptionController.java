package com.example.financial_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financial_app.DTOs.Subscription.SubscriptionDTO;
import com.example.financial_app.services.SubscriptionService;
import com.example.financial_app.utils.ResponseUtil;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    
    @GetMapping("/")
    public ResponseEntity<ResponseUtil.ApiResponse<Object>> getSubscriptions(){
        Map<String, Object> data = new HashMap<>();
        
        List<SubscriptionDTO> subscriptions = this.subscriptionService.getSubscriptions();
        data.put("subscriptions", subscriptions);
        
        return ResponseUtil.success(data);
    }
}
