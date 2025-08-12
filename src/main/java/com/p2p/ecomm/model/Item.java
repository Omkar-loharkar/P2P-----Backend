package com.p2p.ecomm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;


@Entity
@Table(name = "cart_items")
@Getter
@Setter
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private Sku sku;
    private DeliveryDetails deliveryDetails;

    @ElementCollection
    @CollectionTable(
            name = "item_properties",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> properties;
}