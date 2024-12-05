package com.learn.microservices.inventory.service;

import com.learn.microservices.inventory.dto.InventoryResponse;
import com.learn.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryResponse isInStock(String skuCode, Integer quantity){
        log.info(" Start -- Received request to check stock for skuCode {}, with quantity {}", skuCode, quantity);
        boolean inStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        log.info(" End -- Product with skuCode {}, and quantity {}, is in stock - {}", skuCode, quantity, inStock);
        return new InventoryResponse(inStock);
    }

}
