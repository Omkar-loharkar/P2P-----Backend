package com.example.ecommerce.controller;

import com.example.ecommerce.service.ChatBot.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.getOrDefault("message", "");
        String reply = chatbotService.getReply(userMessage);
        return Map.of("reply", reply);
    }
} 