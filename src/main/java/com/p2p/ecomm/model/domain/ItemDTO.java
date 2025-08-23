package com.p2p.ecomm.model.domain;

import com.p2p.ecomm.model.entity.DeliveryDetails;
import com.p2p.ecomm.model.entity.Sku;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ItemDTO implements Serializable {

    private Long itemId;

    private double quantity;

    private List<Sku> skus;

    private DeliveryDetails deliveryDetails;

    private CartDTO cart;
}