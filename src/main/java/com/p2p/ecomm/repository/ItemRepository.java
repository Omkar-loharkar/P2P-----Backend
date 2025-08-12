package com.p2p.ecomm.repository;

import com.p2p.ecomm.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

} 