package com.example.order_system.request;

import lombok.*;

@Data
@RequiredArgsConstructor
@Setter @Getter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
}
