package com.p2p.retailstore.repository;

import com.p2p.retailstore.model.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Long> {
}
