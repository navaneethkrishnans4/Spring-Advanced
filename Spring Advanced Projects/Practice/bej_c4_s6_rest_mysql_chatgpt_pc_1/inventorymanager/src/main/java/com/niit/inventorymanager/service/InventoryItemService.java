// InventoryItemService.java
package com.niit.inventorymanager.service;

import com.niit.inventorymanager.domain.InventoryItem;

import java.util.List;

public interface InventoryItemService {
    InventoryItem createInventoryItem(InventoryItem item);
    List<InventoryItem> getAllInventoryItems();
    InventoryItem getInventoryItemById(Long id);
    InventoryItem updateInventoryItem(Long id, InventoryItem updatedItem);
    List<InventoryItem> getItemsWithLowStock(int threshold);
    List<InventoryItem> searchItems(String keyword);
}
