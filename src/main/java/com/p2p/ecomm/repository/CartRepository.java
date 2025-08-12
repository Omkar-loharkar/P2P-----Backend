package com.p2p.ecomm.repository;

import com.p2p.ecomm.model.Cart;
import com.p2p.ecomm.model.CartId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    List<Cart> findByUserId(Long userId);
    Optional<Cart> findByUserIdAndCartType(Long userId, String cartType);
}