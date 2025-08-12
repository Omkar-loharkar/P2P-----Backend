package com.example.ecommerce.repository;

import com.example.ecommerce.model.entity.CartItem;
import com.example.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
} 