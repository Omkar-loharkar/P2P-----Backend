package com.p2p.retailstore.model.domain.Request;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PurchaseItemRequestDTO {
    private Long productId;
    private String batchNumber;
    private String packaging;
    private Integer quantity;
    private Double rate;
    private Double mrp;
    private Double gstRate;
    private String expiryDate;
}
