package com.p2p.ecomm.service;

import com.p2p.ecomm.Factory.CartFactory;
import com.p2p.ecomm.model.Response.CartResponse;
import com.p2p.ecomm.model.Response.ItemResponse;
import com.p2p.ecomm.model.domain.CartDTO;
import com.p2p.ecomm.model.domain.ItemDTO;
import com.p2p.ecomm.model.entity.Cart;
import com.p2p.ecomm.model.entity.Item;
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
    @Autowired
    private CartFactory cartFactory;

    public CartResponse getCart(String email, String cartType) {
        CartResponse cartResponse = new CartResponse();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getId()!=null) {
            Optional<Cart> opCart = cartRepository.findByUserIdAndCartType(user.getId(), cartType);
            if (opCart.isPresent()) {
                cartResponse = cartFactory.convertCartEntityToResponse(opCart.get());
            }
        }
        return cartResponse;
    }

    public List<CartResponse> getAllCart(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        List<CartResponse> cartResponseList = new ArrayList<>();
        if (user != null && user.getId()!=null) {
            List<Cart> cartList = cartRepository.findByUserId(user.getId());
            if(cartList!=null && !cartList.isEmpty()){
                for (Cart cart : cartList) {
                    CartResponse cartResponse = cartFactory.convertCartEntityToResponse(cart);
                    cartResponseList.add(cartResponse);
                }
            }
        }
        return cartResponseList;
    }

    public CartResponse createCart(String email, CartDTO cartDTO, String cartType) {
        User user = userRepository.findByEmail(email).orElse(null);
        Cart cart = cartFactory.convertCartDTOToEntity(cartDTO);
        cart.setCartType(cartType);
        if (user != null && user.getId()!=null) {
            cart.setUserId(user.getId());
        }
        return cartFactory.convertCartEntityToResponse(cartRepository.save(cart));
    }

    public List<ItemResponse> createCartItem(String email, List<ItemDTO> itemDTOList) {
        User user = userRepository.findByEmail(email).orElse(null);
        List<Item> items = cartFactory.convertItemListDTOToEntity(itemDTOList);
        return cartFactory.convertItemListEntityToResponse(itemRepository.saveAll(items));
    }

} 