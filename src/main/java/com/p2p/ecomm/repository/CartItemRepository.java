package com.p2p.ecomm.repository;

import com.p2p.ecomm.model.CartItem;
import com.p2p.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
} 