package com.niit.inventorymanager.repository;

import com.niit.inventorymanager.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByQuantityLessThan(int threshold);
    List<InventoryItem> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}