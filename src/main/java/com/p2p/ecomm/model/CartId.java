
package com.p2p.ecomm.model;

import java.io.Serializable;
import java.util.Objects;

public class CartId implements Serializable {

    private String cartType;
    private Long userId;

    // A no-arg constructor is required by JPA
    public CartId() {}

    public CartId(Long userId, String cartType) {
        this.cartType = cartType;
        this.userId = userId;
    }

    // The getters and setters for cartType and userId have been removed.

    // Must override equals() and hashCode() for JPA to work correctly
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(cartType, cartId.cartType) && Objects.equals(userId, cartId.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartType, userId);
    }
}