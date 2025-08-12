package com.p2p.web;

import com.p2p.user.model.User;
import com.p2p.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return Map.of("error", "User not found");
        }
        return Map.of(
            "name", user.getName(),
            "email", user.getEmail()
        );
    }
} 