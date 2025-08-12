package com.p2p.ecomm.repository;

import com.p2p.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
} 