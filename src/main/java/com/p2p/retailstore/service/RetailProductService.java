package com.p2p.retailstore.service;

import com.p2p.retailstore.model.domain.Request.RetailProductRequestDTO;
import com.p2p.retailstore.model.domain.Response.RetailProductResponseDTO;

import java.util.List;

public interface RetailProductService {

    public RetailProductResponseDTO createRetailProduct(RetailProductRequestDTO retailProductRequestDTO);

    public List<RetailProductResponseDTO> getAllProducts();

    public RetailProductResponseDTO getProductById(Long id);

    public RetailProductResponseDTO updateProduct(Long id, RetailProductRequestDTO dto);

    public void deleteProduct(Long id);


}
