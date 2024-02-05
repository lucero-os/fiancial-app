package com.example.financial_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.financial_app.utils.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseUtil.ApiResponse<?>> handleException(Exception e) {
        // Handle the exception and return an appropriate response
        String message = "Internal server error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Class<? extends Exception> exceptionType = e.getClass();

        switch(this.getExceptionName(exceptionType.getName())){
            case "CustomException":
                status = HttpStatus.OK;
                message = e.getMessage();
                break;
            default:
                e.printStackTrace();
        }

        return ResponseUtil.error(status, message);
    }

    private String getExceptionName(String pathName){
        int lastDotIndex = pathName.lastIndexOf('.');
        
        if (lastDotIndex != -1 && lastDotIndex < pathName.length() - 1) {
            return pathName.substring(lastDotIndex + 1);
        } else {
            // No dot found or dot is at the end, return the whole string
            return pathName;
        }
    }
}