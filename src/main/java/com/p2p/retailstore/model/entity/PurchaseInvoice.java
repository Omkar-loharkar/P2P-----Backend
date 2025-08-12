package com.p2p.retailstore.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchase_invoice")
@Getter
@Setter
public class PurchaseInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String wholesalerName; // "Pratik Enterprises" etc.

    @OneToMany(mappedBy = "purchaseInvoice", cascade = CascadeType.ALL)
    private List<PurchaseItem> items;

    // Getters and setters
}