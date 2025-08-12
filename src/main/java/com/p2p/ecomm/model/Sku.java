package com.p2p.ecomm.model;

import java.io.Serializable;
import java.util.Map;

public class Sku implements Serializable {
    private boolean defaultSku;
    private String isBuyable;
    private Map<String, Object> properties;
    private String skuId;
    private String productId;
    private int skuQuantity;
    private double price;
}
