package com.example.ecommerce.service;

import com.example.ecommerce.model.domain.ProductDO;
import com.example.ecommerce.model.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ProductService {

    public List<Product> addMiultipleProducts(List<ProductDO> productDOs);

//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @PostMapping
//    public  Iterable<Product> SaveAllProducts(List<Product> products) {
//        Iterable<Product> prods=productRepository.saveAll(products);
//        return prods;
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
//        return productRepository.findById(id).orElse(null);
//    }

}
