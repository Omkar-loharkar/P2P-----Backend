package com.p2p.retailstore.model.domain.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RetailProductRequestDTO {
    private String name;
    private String code;
    private String batchNumber;
    private LocalDate expiryDate;
    private String manufacturer;
    private String packaging;
    private Double mrp;
    private Double rate;
    private Integer quantity;
    private Double gstRate;

}