package com.p2p.user.service;

import com.p2p.user.model.User;
import com.p2p.user.repository.UserRepository;
import com.p2p.user.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "Email already registered.";
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "Registration successful.";
    }

    public String login(String email, String password) {
        return userRepository.findByEmail(email)
            .filter(user -> passwordEncoder.matches(password, user.getPassword()))
            .map(user -> jwtUtil.generateToken(email))
            .orElse(null);
    }
} 