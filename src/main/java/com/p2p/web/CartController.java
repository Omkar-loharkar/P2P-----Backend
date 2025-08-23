package com.p2p.web;

import com.p2p.ecomm.model.Response.CartResponse;
import com.p2p.ecomm.model.Response.ItemResponse;
import com.p2p.ecomm.model.domain.CartDTO;
import com.p2p.ecomm.model.domain.ItemDTO;
import com.p2p.ecomm.service.CartService;
import com.p2p.user.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
@Tag(name = "Cart", description = "API for managing user shopping carts and items.")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/{cartType}")
    @Operation(summary = "Get a specific cart", description = "Retrieves a single cart for the authenticated user based on cart type.")
    public CartResponse getCart(
            @RequestHeader ("token") String token,
            @PathVariable("cartType") String cartType
    ) {
        Claims auth =jwtUtil.parseToken(token);
        String email =auth.getSubject();
        return cartService.getCart(email, cartType);
    }

    @GetMapping
    @Operation(summary = "Get all carts", description = "Retrieves a list of all carts for the authenticated user.")
    public List<CartResponse> getAllCart(
            @RequestHeader ("token") String token
    ) {
        Claims auth =jwtUtil.parseToken(token);
        String email =auth.getSubject();
        return cartService.getAllCart(email);
    }

    @PostMapping
    @Operation(summary = "Create a new cart", description = "Creates a new cart for the authenticated user.")
    public CartResponse createCart(
            @RequestHeader ("token") String token,
            @RequestBody CartDTO cartDTO,
            @PathVariable("cartType") String cartType

    ) {
        Claims auth= jwtUtil.parseToken(token);
        String email =auth.getSubject();
        return cartService.createCart(email, cartDTO, cartType);
    }

    @PostMapping("{cartType}/items")
    @Operation(summary = "Add items to a cart", description = "Adds a list of items to the authenticated user's cart.")
    public List<ItemResponse> createCartItems(
            @RequestHeader ("token") String token,
            @RequestBody List<ItemDTO> items)
    {
        Claims auth =jwtUtil.parseToken(token);
        String email =auth.getSubject();
        return cartService.createCartItem(email, items);
    }
}
