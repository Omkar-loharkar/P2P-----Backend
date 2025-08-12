package com.p2p.web;

import com.p2p.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> payload) {
        String name = payload.getOrDefault("name", "");
        String email = payload.getOrDefault("email", "");
        String password = payload.getOrDefault("password", "");
        String message = authService.register(name, email, password);
        return Map.of("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> payload) {
        String email = payload.getOrDefault("email", "");
        String password = payload.getOrDefault("password", "");
        String token = authService.login(email, password);
        if (token != null) {
            return Map.of("token", token);
        } else {
            return Map.of("message", "Invalid email or password.");
        }
    }
} 