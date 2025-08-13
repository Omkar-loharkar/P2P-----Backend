package com.p2p.retailstore.repository;

import com.p2p.retailstore.model.entity.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice,Long> {
}
