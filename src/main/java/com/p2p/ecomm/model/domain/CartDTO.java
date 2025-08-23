package com.p2p.ecomm.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CartDTO implements Serializable {

    private Long cartId;

    private Long userId;

    private String cartType;

    private List<ItemDTO> items;
}
