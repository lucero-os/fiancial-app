package com.example.financial_app.utils;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil{

    public static <T> ResponseEntity<ApiResponse<T>> success(Map<String, Object> data) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.OK.value(), "Success", data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<ApiResponse<?>> error(HttpStatus status, String message) {
        ApiResponse<?> response = new ApiResponse<>(status.value(), message, null);
        return ResponseEntity.status(status).body(response);
    }

    public static class ApiResponse<T> {
        private final int status;
        private final String message;
        private final Map<String, Object> data;

        public ApiResponse(int status, String message, Map<String, Object> data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, Object> getData() {
            return data;
        }
    }
}

