package com.p2p.retailstore.web;



import com.p2p.retailstore.model.domain.Request.PurchaseInvoiceRequestDTO;
import com.p2p.retailstore.model.domain.Response.PurchaseInvoiceResponseDTO;
import com.p2p.retailstore.service.PurchaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/retailstore/purchase-invoices")
@CrossOrigin(origins = "*")
@Tag(name = "Purchase Invoice")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    public void PurchaseInvoiceController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<PurchaseInvoiceResponseDTO> getAll() {
        return purchaseService.getAllPurchaseInvoices();
    }

    @GetMapping("/{id}")
    public PurchaseInvoiceResponseDTO getById(@PathVariable Long id) {
        return purchaseService.getPurchaseInvoiceById(id);
    }

    @PostMapping
    public PurchaseInvoiceResponseDTO create(@RequestHeader ("token") String token,    @RequestBody PurchaseInvoiceRequestDTO dto) {
        return purchaseService.createPurchaseInvoice(dto);
    }

    @PutMapping("/{id}")
    public PurchaseInvoiceResponseDTO update(@PathVariable Long id, @RequestBody PurchaseInvoiceRequestDTO dto) {
        return purchaseService.updatePurchaseInvoice(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boolean deleted = purchaseService.deletePurchaseInvoice(id);
        if(!deleted) {
            throw new RuntimeException("Purchase invoice not found with id " + id);
        }
    }
}