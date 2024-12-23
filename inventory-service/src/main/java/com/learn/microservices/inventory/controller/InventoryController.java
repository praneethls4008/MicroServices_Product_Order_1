package com.learn.microservices.inventory.controller;

import com.learn.microservices.inventory.dto.InventoryResponse;
import com.learn.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private  final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
