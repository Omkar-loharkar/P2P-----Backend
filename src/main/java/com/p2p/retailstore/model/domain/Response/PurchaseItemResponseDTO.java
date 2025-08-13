package com.p2p.retailstore.model.domain.Response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PurchaseItemResponseDTO {
    private Long id;
    private ProductSummaryDTO product;
    private String batchNumber;
    private String packaging;
    private Integer quantity;
    private Double rate;
    private Double mrp;
    private Double gstRate;
    private String expiryDate;
}
