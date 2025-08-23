package com.p2p.ecomm.model.Response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CartResponse implements Serializable {

    private Long cartId;

    private Long userId;

    private String cartType;

    private List<ItemResponse> items;
}
