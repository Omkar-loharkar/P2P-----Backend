package com.p2p.ecomm.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "sku")
@Getter
@Setter
public class Sku implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean defaultSku;

    private String isBuyable;

    private String skuId;

    private String productId;

    private int skuQuantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "itemId")
    private Item item;
}
