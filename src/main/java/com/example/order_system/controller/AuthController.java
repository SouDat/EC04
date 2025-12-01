package com.example.order_system.controller;

import com.example.order_system.dto.ApiResponse;
import com.example.order_system.model.User;
import com.example.order_system.repository.UserRepository;
import com.example.order_system.request.LoginRequest;
import com.example.order_system.request.RegisterRequest;
import com.example.order_system.service.JwtService;
import com.example.order_system.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse("false", "User exists"));
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole() == null ? "ROLE_USER" : req.getRole());
        userRepo.save(user);
        return ResponseEntity.ok(new ApiResponse("true", "Registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(new ApiResponse("false", "Invalid credentials"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        User user = userRepo.findByUsername(req.getUsername()).get();
        String token = jwtService.createTokenForUser(user);

        return ResponseEntity.ok(java.util.Map.of("token", token));
    }
}
