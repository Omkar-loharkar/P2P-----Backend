package com.p2p.web;

import com.p2p.ecomm.model.Cart;
import com.p2p.ecomm.model.Item;
import com.p2p.ecomm.service.CartService;
import com.p2p.user.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
//@Tag(name = "Cart", description = "API for managing user shopping carts and items.")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/{cartType}")
    @Operation(summary = "Get a specific cart", description = "Retrieves a single cart for the authenticated user based on cart type.")
    public Cart getCart(
            @RequestParam @Parameter(description = "token", required = false) Authentication authtoken,
            @RequestHeader @Parameter(description = "The type of cart (e.g., 'primary', 'wishlist')") String cartType
    ) {
        String email = (String) authtoken.getPrincipal();
        return cartService.getCart(email, cartType);
    }

    @GetMapping
    @Operation(summary = "Get all carts", description = "Retrieves a list of all carts for the authenticated user.")
    public List<Cart> getAllCart(@RequestParam @Parameter(description = "AuthToken", required = false) Authentication authtoken) {
        String email = (String) authtoken.getPrincipal();
        return cartService.getAllCart(email);
    }

    @PostMapping
    @Operation(summary = "Create a new cart", description = "Creates a new cart for the authenticated user.")
    public Cart createCart(
            @RequestHeader ("token") String token,
            @RequestBody Cart cart
    ) {
//        String email = (String) token.getPrincipal();
        Claims auth= jwtUtil.parseToken(token);
        String email =auth.getSubject();
        return cartService.createCart(email, cart);
    }

    @PostMapping("/items")
    @Operation(summary = "Add items to a cart", description = "Adds a list of items to the authenticated user's cart.")
    public List<Item> createCartItems(
            @RequestHeader ("token") String token,

            @RequestBody
//                    (
//                    description = "A list of items to be added to the cart.",
//                    required = true,
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(
//                                    implementation = Item.class,
//                                    example = "[{\"quantity\": 2.0, \"properties\": {\"color\": \"red\", \"size\": \"L\"}}, {\"quantity\": 1.0, \"properties\": {\"color\": \"blue\", \"size\": \"M\"}}]"
//                            )
//                    )
//            )
    List<Item> items
    ) {
        Claims auth =jwtUtil.parseToken(token);
        //String email = (String) authtoken.getPrincipal();
        return cartService.createCartItem(auth.getSubject(), items);
    }
}
