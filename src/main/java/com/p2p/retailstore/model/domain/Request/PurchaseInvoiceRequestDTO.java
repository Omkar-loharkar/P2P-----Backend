package com.p2p.retailstore.model.domain.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PurchaseInvoiceRequestDTO {
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String wholesalerName;
    private List<PurchaseItemRequestDTO> items;
}
