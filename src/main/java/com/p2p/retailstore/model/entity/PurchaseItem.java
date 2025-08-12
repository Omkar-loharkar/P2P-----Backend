package com.p2p.retailstore.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "purchase_item")
@Getter
@Setter
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PurchaseInvoice purchaseInvoice;

    @ManyToOne
    private RetailProduct retailProduct;

    private String batchNumber;
    private String packaging;
    private Integer quantity;
    private Double rate;
    private Double mrp;
    private Double gstRate;

    private String expiryDate; // Can convert to LocalDate if standardized

    // Getters and setters...
}