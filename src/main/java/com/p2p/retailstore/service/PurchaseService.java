package com.p2p.retailstore.service;

import com.p2p.retailstore.model.domain.Request.PurchaseInvoiceRequestDTO;
import com.p2p.retailstore.model.domain.Response.PurchaseInvoiceResponseDTO;

import java.util.List;

public interface PurchaseService {

    public PurchaseInvoiceResponseDTO createPurchaseInvoice(PurchaseInvoiceRequestDTO dto);

    public List<PurchaseInvoiceResponseDTO> getAllPurchaseInvoices();

    public PurchaseInvoiceResponseDTO getPurchaseInvoiceById(Long id);

    public PurchaseInvoiceResponseDTO updatePurchaseInvoice(Long id, PurchaseInvoiceRequestDTO dto);

    public boolean deletePurchaseInvoice(Long id);

}
