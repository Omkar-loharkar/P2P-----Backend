package com.example.ecommerce.service;


import com.example.ecommerce.model.domain.ProductDO;
import com.example.ecommerce.model.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository prodRepo;

    @Override
    public List<Product> addMiultipleProducts(List<ProductDO> productDOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDO productDO : productDOs) {
            Product product = productDO.toEntity(productDO);
            Product p = prodRepo.save(product);
            products.add(p);
        }
        return products;
    }
}
