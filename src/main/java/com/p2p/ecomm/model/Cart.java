package com.p2p.ecomm.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cart")
@IdClass(CartId.class)
public class Cart implements Serializable {

    @Id
    private Long userId;

    @Id
    private String cartType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "cart_items_join_table",
            joinColumns = {
                    @JoinColumn(name = "cart_user_id", referencedColumnName = "userId"),
                    @JoinColumn(name = "cart_type", referencedColumnName = "cartType")
            },
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> items;

    @ElementCollection
    @CollectionTable(
            name = "cart_properties",
            joinColumns = {
                    @JoinColumn(name = "cart_user_id", referencedColumnName = "userId"),
                    @JoinColumn(name = "cart_type", referencedColumnName = "cartType")
            }
    )
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> cartProperties;
}
