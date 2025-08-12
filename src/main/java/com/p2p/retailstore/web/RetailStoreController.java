package com.p2p.retailstore.web;

import com.p2p.ecomm.model.CartItem;
import com.p2p.ecomm.service.CartService;
import com.p2p.retailstore.model.domain.Request.RetailProductRequestDTO;
import com.p2p.retailstore.model.domain.Response.RetailProductResponseDTO;
import com.p2p.retailstore.service.RetailProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/retailstore/products")
@CrossOrigin(origins = "*")
public class RetailStoreController {

    private final RetailProductService service;

    public RetailStoreController(RetailProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<RetailProductResponseDTO> getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public RetailProductResponseDTO getById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @Operation(summary = "Create/Add Product For Retail Store",
    description = "This API adds details of product master")
    @PostMapping
    public RetailProductResponseDTO create( @RequestHeader("token") String token, @RequestBody RetailProductRequestDTO dto) {
        return service.createRetailProduct(dto);
    }

    @PutMapping("/{id}")
    public RetailProductResponseDTO update(@PathVariable Long id, @RequestBody RetailProductRequestDTO dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProduct(id);
    }
}