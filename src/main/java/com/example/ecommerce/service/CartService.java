package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CartItem> getCart(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return List.of();
        return cartItemRepository.findByUser(user);
    }

    @Transactional
    public void setCart(String email, List<Map<String, Object>> items) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return;
        cartItemRepository.deleteByUser(user);
        for (Map<String, Object> item : items) {
            Long productId = ((Number)item.get("id")).longValue();
            int qty = ((Number)item.get("qty")).intValue();
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(qty);
            cartItem.setUser(user);
            cartItemRepository.save(cartItem);
        }
    }
} 