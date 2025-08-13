package com.p2p.retailstore.web;



import com.p2p.retailstore.model.domain.Request.RetailProductRequestDTO;
import com.p2p.retailstore.model.domain.Response.RetailProductResponseDTO;
import com.p2p.retailstore.service.RetailProductService;
import com.p2p.user.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/retailstore/products")
@CrossOrigin(origins = "*")
@Tag(name = "Retail Product")
public class RetailProductController {

    private final RetailProductService service;

    @Autowired
    private JwtUtil jwtUtil;;

    public RetailProductController(RetailProductService service) {
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
        Claims auth = jwtUtil.parseToken(token);
        String email =auth.getSubject();
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