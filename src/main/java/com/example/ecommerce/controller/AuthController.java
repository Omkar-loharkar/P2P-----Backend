package com.example.ecommerce.controller;

import com.example.ecommerce.service.User.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@Tag(name = "User", description = "Endpoints for user registration and login")
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation (summary = "Register a new user",
               description = "Allows a new user to register with a name, email, and password.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> payload) {
        String name = payload.getOrDefault("name", "");
        String email = payload.getOrDefault("email", "");
        String password = payload.getOrDefault("password", "");
        String message = authService.register(name, email, password);
        return Map.of("message", message);
    }

    @Operation( summary = "User login",
               description = "Allows a user to log in with their email and password, returning a JWT token.")
    @ResponseStatus(HttpStatus.OK)
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