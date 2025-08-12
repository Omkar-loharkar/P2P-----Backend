package com.p2p.retailstore.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "retail_product")
@Getter
@Setter
public class RetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String batchNumber;
    private LocalDate expiryDate;
    private String manufacturer;

    @Column(length = 100)
    private String packaging;

    private Double mrp;
    private Double rate;
    private Integer quantity;


    private Double gstRate;


}