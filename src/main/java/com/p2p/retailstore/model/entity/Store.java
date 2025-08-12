package com.p2p.retailstore.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Store name
    private String address;

    // If one store manages many products:
    // @OneToMany(mappedBy = "store")
    // private List<Product> products;

    // Getters and setters...
}