// InventoryItemController.java
package com.niit.inventorymanager.controller;

import com.niit.inventorymanager.domain.InventoryItem;
import com.niit.inventorymanager.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-items")
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    @PostMapping
    public InventoryItem createInventoryItem(@RequestBody InventoryItem item) {
        return inventoryItemService.createInventoryItem(item);
    }

    @GetMapping
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemService.getAllInventoryItems();
    }

    @GetMapping("/{id}")
    public InventoryItem getInventoryItemById(@PathVariable Long id) {
        return inventoryItemService.getInventoryItemById(id);
    }

    @PutMapping("/{id}")
    public InventoryItem updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItem updatedItem) {
        return inventoryItemService.updateInventoryItem(id, updatedItem);
    }

    @GetMapping("/low-stock")
    public List<InventoryItem> getItemsWithLowStock(@RequestParam int threshold) {
        return inventoryItemService.getItemsWithLowStock(threshold);
    }

    @GetMapping("/search")
    public List<InventoryItem> searchItems(@RequestParam String keyword) {
        return inventoryItemService.searchItems(keyword);
    }
}
