package com.example.order_system.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
