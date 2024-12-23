package com.learn.microservices.order.client;

import com.learn.microservices.order.dto.InventoryClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "inventory", url = "${inventory.service.url}")
public interface InventoryClient {

    @GetMapping("/api/inventory")
    public InventoryClientResponse isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
