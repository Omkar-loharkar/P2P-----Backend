package com.p2p.ecomm.service;

import com.p2p.ecomm.model.Cart;
import com.p2p.ecomm.model.Item;
import com.p2p.ecomm.repository.CartRepository;
import com.p2p.ecomm.repository.ItemRepository;
import com.p2p.user.model.User;
import com.p2p.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Cart getCart(String email, String cartType) {
        Cart cart = new Cart();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getId()!=null) {
            Optional<Cart> opCart = cartRepository.findByUserIdAndCartType(user.getId(), cartType);
            if (opCart.isPresent()) {
                cart = opCart.get();
            }
        }
        return cart;
    }

    public List<Cart> getAllCart(String email) {
        List<Cart> cartList = new ArrayList<>();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getId()!=null) {
            cartList = cartRepository.findByUserId(user.getId());
        }
        return cartList;
    }

    public Cart createCart(String email, Cart cart) {
        User user = userRepository.findByEmail(email).orElse(null);
        cart.setCartType("USER");
        cart.setUserId(1L);
        return cartRepository.save(cart);
    }

    public List<Item> createCartItem(String email, List<Item> items) {
        User user = userRepository.findByEmail(email).orElse(null);
        return itemRepository.saveAll(items);
    }

} 