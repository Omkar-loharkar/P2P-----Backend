package com.p2p.retailstore.repository;

import com.p2p.retailstore.model.entity.RetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailProductRepository extends JpaRepository<RetailProduct, Long> {
}
