package com.p2p.web;

import com.p2p.ecomm.model.Cart;
import com.p2p.ecomm.model.Item;
import com.p2p.ecomm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{cartType}")
    public Cart getCart(Authentication authentication, @RequestHeader String cartType) {
        String email = (String) authentication.getPrincipal();
        return cartService.getCart(email, cartType);
    }

    @GetMapping
    public List<Cart> getAllCart(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return cartService.getAllCart(email);
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return cartService.createCart(email, cart);
    }

    @PostMapping("/items")
    public List<Item> createCartItems(@RequestBody List<Item> items, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return cartService.createCartItem(email, items);
    }
} 