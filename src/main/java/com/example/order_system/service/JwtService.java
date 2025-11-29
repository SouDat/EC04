package com.example.order_system.service;

import com.example.order_system.config.JwtUtil;
import com.example.order_system.model.User;
import com.example.order_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    public String createTokenForUser(User user) {
        return jwtUtil.generateToken(user.getUsername());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return jwtUtil.validateToken(token, userDetails.getUsername());
    }

}
