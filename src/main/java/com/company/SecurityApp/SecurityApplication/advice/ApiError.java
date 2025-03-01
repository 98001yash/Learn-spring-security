package com.company.SecurityApp.SecurityApplication.advice;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(){
        this.timeStamp = LocalDateTime.now();

    }

    public ApiError(String error, HttpStatus statusCode){
        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}

/**
 * @Data
 * public class ApiError {
 *     private LocalDateTime timeStamp;
 *     private String error;
 *     private HttpStatus statusCode;
 *
 *     public ApiError(){
 *         this.timeStamp = timeStamp;
 *     }
 *
 *     public ApiError(String error,HttpStatus statusCode){
 *         this();
 *         this.error  =  error;
 *         this.statusCode = statusCode;
 *     }
 * }
 */