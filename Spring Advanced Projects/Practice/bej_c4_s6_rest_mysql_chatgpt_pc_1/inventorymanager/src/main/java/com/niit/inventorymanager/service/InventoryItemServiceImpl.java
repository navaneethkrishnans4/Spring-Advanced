// InventoryItemServiceImpl.java
package com.niit.inventorymanager.service;

import com.niit.inventorymanager.domain.InventoryItem;
import com.niit.inventorymanager.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItem createInventoryItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    @Override
    public InventoryItem getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id).orElse(null);
    }

    @Override
    public InventoryItem updateInventoryItem(Long id, InventoryItem updatedItem) {
        InventoryItem existingItem = inventoryItemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setName(updatedItem.getName());
            existingItem.setDescription(updatedItem.getDescription());
            existingItem.setQuantity(updatedItem.getQuantity());
            return inventoryItemRepository.save(existingItem);
        }
        return null;
    }

    @Override
    public List<InventoryItem> getItemsWithLowStock(int threshold) {
        return inventoryItemRepository.findByQuantityLessThan(threshold);
    }

    @Override
    public List<InventoryItem> searchItems(String keyword) {
        return inventoryItemRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
}
