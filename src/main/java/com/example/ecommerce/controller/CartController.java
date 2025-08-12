package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart.CartItem;
import com.example.ecommerce.service.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Map<String, Number>> getCart(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        List<CartItem> items = cartService.getCart(email);
//        return items.stream().map(item -> Map.of(
//            "id", item.getProductId(),
//            "qty", item.getQuantity()
//        )).collect(java.util.stream.Collectors.toList());
        return null;
    }

    @PostMapping
    public void setCart(@RequestBody List<Map<String, Object>> items, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        cartService.setCart(email, items);
    }
} 