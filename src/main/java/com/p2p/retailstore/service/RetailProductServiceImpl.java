package com.p2p.retailstore.service;

import com.p2p.retailstore.model.domain.Request.RetailProductRequestDTO;
import com.p2p.retailstore.model.domain.Response.RetailProductResponseDTO;
import com.p2p.retailstore.model.entity.RetailProduct;
import com.p2p.retailstore.repository.RetailProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetailProductServiceImpl implements  RetailProductService {

    @Autowired
    RetailProductRepository retailProductRepository;

    // Convert Entity → DTO
    private RetailProductResponseDTO toResponseDTO(RetailProduct product) {
        RetailProductResponseDTO dto = new RetailProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCode(product.getCode());
        dto.setBatchNumber(product.getBatchNumber());
        dto.setExpiryDate(product.getExpiryDate());
        dto.setManufacturer(product.getManufacturer());
        dto.setPackaging(product.getPackaging());
        dto.setMrp(product.getMrp());
        dto.setRate(product.getRate());
        dto.setQuantity(product.getQuantity());
        dto.setGstRate(product.getGstRate());
        return dto;
    }

    // Convert DTO → Entity
    private RetailProduct toEntity(RetailProductRequestDTO dto) {
        RetailProduct product = new RetailProduct();
        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setBatchNumber(dto.getBatchNumber());
        product.setExpiryDate(dto.getExpiryDate());
        product.setManufacturer(dto.getManufacturer());
        product.setPackaging(dto.getPackaging());
        product.setMrp(dto.getMrp());
        product.setRate(dto.getRate());
        product.setQuantity(dto.getQuantity());
        product.setGstRate(dto.getGstRate());
        return product;
    }


    @Override
    public RetailProductResponseDTO createRetailProduct(RetailProductRequestDTO retailProductRequestDTO) {
        RetailProduct product = toEntity(retailProductRequestDTO);
        return toResponseDTO(retailProductRepository.save(product));
    }

    @Override
    public List<RetailProductResponseDTO> getAllProducts() {
        return retailProductRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RetailProductResponseDTO getProductById(Long id) {
        return retailProductRepository.findById(id)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    @Override
    public RetailProductResponseDTO updateProduct(Long id, RetailProductRequestDTO dto) {
        return retailProductRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setCode(dto.getCode());
            existing.setBatchNumber(dto.getBatchNumber());
            existing.setExpiryDate(dto.getExpiryDate());
            existing.setManufacturer(dto.getManufacturer());
            existing.setPackaging(dto.getPackaging());
            existing.setMrp(dto.getMrp());
            existing.setRate(dto.getRate());
            existing.setQuantity(dto.getQuantity());
            existing.setGstRate(dto.getGstRate());
            return toResponseDTO(retailProductRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        retailProductRepository.deleteById(id);
    }
}
