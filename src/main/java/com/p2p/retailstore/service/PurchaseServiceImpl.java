package com.p2p.retailstore.service;

import com.p2p.retailstore.model.domain.Request.PurchaseInvoiceRequestDTO;
import com.p2p.retailstore.model.domain.Request.PurchaseItemRequestDTO;
import com.p2p.retailstore.model.domain.Response.ProductSummaryDTO;
import com.p2p.retailstore.model.domain.Response.PurchaseInvoiceResponseDTO;
import com.p2p.retailstore.model.domain.Response.PurchaseItemResponseDTO;
import com.p2p.retailstore.model.entity.PurchaseInvoice;
import com.p2p.retailstore.model.entity.PurchaseItem;
import com.p2p.retailstore.model.entity.RetailProduct;
import com.p2p.retailstore.repository.PurchaseInvoiceRepository;
import com.p2p.retailstore.repository.RetailProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private  PurchaseInvoiceRepository purchaseInvoiceRepo;

    @Autowired
    private  RetailProductRepository productRepo;

    public void PurchaseService(PurchaseInvoiceRepository purchaseInvoiceRepo, RetailProductRepository productRepo) {
        this.purchaseInvoiceRepo = purchaseInvoiceRepo;
        this.productRepo = productRepo;
    }

    // Mapper methods for PurchaseItem and PurchaseInvoice
    private PurchaseItem toPurchaseItemEntity(PurchaseItemRequestDTO dto, PurchaseInvoice invoice) {
        PurchaseItem item = new PurchaseItem();
        item.setPurchaseInvoice(invoice);
        productRepo.findById(dto.getProductId())
                .ifPresent(item::setRetailProduct);
        item.setBatchNumber(dto.getBatchNumber());
        item.setPackaging(dto.getPackaging());
        item.setQuantity(dto.getQuantity());
        item.setRate(dto.getRate());
        item.setMrp(dto.getMrp());
        item.setGstRate(dto.getGstRate());
        item.setExpiryDate(dto.getExpiryDate());
        return item;
    }

    private PurchaseItemResponseDTO toPurchaseItemResponseDTO(PurchaseItem item) {
        PurchaseItemResponseDTO dto = new PurchaseItemResponseDTO();
        dto.setId(item.getId());
        ProductSummaryDTO productSummary = new ProductSummaryDTO();
        if (item.getRetailProduct() != null) {
            productSummary.setId(item.getRetailProduct().getId());
            productSummary.setName(item.getRetailProduct().getName());
        }
        dto.setProduct(productSummary);
        dto.setBatchNumber(item.getBatchNumber());
        dto.setPackaging(item.getPackaging());
        dto.setQuantity(item.getQuantity());
        dto.setRate(item.getRate());
        dto.setMrp(item.getMrp());
        dto.setGstRate(item.getGstRate());
        dto.setExpiryDate(item.getExpiryDate());
        return dto;
    }

    private PurchaseInvoiceResponseDTO toPurchaseInvoiceResponseDTO(PurchaseInvoice invoice) {
        PurchaseInvoiceResponseDTO dto = new PurchaseInvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setWholesalerName(invoice.getWholesalerName());
        List<PurchaseItemResponseDTO> items = invoice.getItems().stream()
                .map(this::toPurchaseItemResponseDTO)
                .collect(Collectors.toList());
        dto.setItems(items);
        return dto;
    }

    @Transactional
    public PurchaseInvoiceResponseDTO createPurchaseInvoice(PurchaseInvoiceRequestDTO dto) {
        PurchaseInvoice invoice = new PurchaseInvoice();
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setWholesalerName(dto.getWholesalerName());

        List<PurchaseItem> items = dto.getItems().stream()
                .map(itemDto -> toPurchaseItemEntity(itemDto, invoice))
                .collect(Collectors.toList());

        invoice.setItems(items);

        // Save invoice with items
        PurchaseInvoice savedInvoice = purchaseInvoiceRepo.save(invoice);

        // Update Product quantities
        for (PurchaseItem item : savedInvoice.getItems()) {
            RetailProduct product = item.getRetailProduct();
            if (product != null) {
                Integer existingQty = product.getQuantity() == null ? 0 : product.getQuantity();
                product.setQuantity(existingQty + (item.getQuantity() == null ? 0 : item.getQuantity()));
                productRepo.save(product);
            }
        }

        return toPurchaseInvoiceResponseDTO(savedInvoice);
    }

    public List<PurchaseInvoiceResponseDTO> getAllPurchaseInvoices() {
        return purchaseInvoiceRepo.findAll().stream()
                .map(this::toPurchaseInvoiceResponseDTO)
                .collect(Collectors.toList());
    }

    public PurchaseInvoiceResponseDTO getPurchaseInvoiceById(Long id) {
        return purchaseInvoiceRepo.findById(id)
                .map(this::toPurchaseInvoiceResponseDTO)
                .orElse(null);
    }

    @Transactional
    public PurchaseInvoiceResponseDTO updatePurchaseInvoice(Long id, PurchaseInvoiceRequestDTO dto) {
        return purchaseInvoiceRepo.findById(id).map(existingInvoice -> {
            // Rollback stock quantities of existing items
            existingInvoice.getItems().forEach(item -> {
                RetailProduct product = item.getRetailProduct();
                if (product != null && product.getQuantity() != null && item.getQuantity() != null) {
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productRepo.save(product);
                }
            });
            // Remove existing items
            existingInvoice.getItems().clear();

            existingInvoice.setInvoiceNumber(dto.getInvoiceNumber());
            existingInvoice.setInvoiceDate(dto.getInvoiceDate());
            existingInvoice.setWholesalerName(dto.getWholesalerName());

            // Add new items
            List<PurchaseItem> newItems = dto.getItems().stream()
                    .map(itemDto -> toPurchaseItemEntity(itemDto, existingInvoice))
                    .collect(Collectors.toList());
            existingInvoice.setItems(newItems);

            PurchaseInvoice savedInvoice = purchaseInvoiceRepo.save(existingInvoice);

            // Update stock quantities for new items
            savedInvoice.getItems().forEach(item -> {
                RetailProduct product = item.getRetailProduct();
                if (product != null) {
                    Integer existingQty = product.getQuantity() == null ? 0 : product.getQuantity();
                    product.setQuantity(existingQty + (item.getQuantity() == null ? 0 : item.getQuantity()));
                    productRepo.save(product);
                }
            });

            return toPurchaseInvoiceResponseDTO(savedInvoice);
        }).orElse(null);
    }

    @Transactional
    public boolean deletePurchaseInvoice(Long id) {
        return purchaseInvoiceRepo.findById(id).map(invoice -> {
            // Rollback stock quantities
            invoice.getItems().forEach(item -> {
                RetailProduct product = item.getRetailProduct();
                if (product != null && product.getQuantity() != null && item.getQuantity() != null) {
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productRepo.save(product);
                }
            });
            purchaseInvoiceRepo.delete(invoice);
            return true;
        }).orElse(false);
    }
}
