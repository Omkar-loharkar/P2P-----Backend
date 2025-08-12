package com.example.ecommerce.service.ChatBot;

import org.springframework.stereotype.Service;

@Service
public class ChatbotService {
    public String getReply(String message) {
        if (message == null || message.trim().isEmpty()) {
            return "Please say something!";
        }
        String msg = message.toLowerCase();
        if (msg.contains("hello") || msg.contains("hi")) {
            return "Hello! How can I assist you with your shopping today?";
        } else if (msg.contains("product")) {
            return "We have a wide range of products! What are you looking for?";
        } else if (msg.contains("order")) {
            return "You can view your orders in your account section.";
        } else if (msg.contains("bye")) {
            return "Goodbye! Have a great day!";
        }
        return "I'm here to help with your shopping questions!";
    }
} 