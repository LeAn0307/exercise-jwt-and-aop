package com.example.exercise.dto;

import lombok.Data;

@Data
public class LoginRespone {
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginRespone(String accessToken) {
        this.accessToken = accessToken;
    }
}