package com.learn.microservices.order.service;

import com.learn.microservices.order.client.InventoryClient;
import com.learn.microservices.order.dto.InventoryClientResponse;
import com.learn.microservices.order.dto.OrderRequest;
import com.learn.microservices.order.dto.OrderResponse;
import com.learn.microservices.order.model.Order;
import com.learn.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderResponse placeOrder(OrderRequest orderRequest){

        log.info("Calling inventory service to check product is in stock!");
        InventoryClientResponse inventoryClientResponse = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(inventoryClientResponse.isInStock() == false){
            log.info("product is out of stock!");
            return new OrderResponse(null, null, null, new BigDecimal(0), 0);
        };
        log.info("product is in stock!");

        log.info("Received place order request for skuCode:{}", orderRequest.skuCode());
        Order order = Order
                .builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(orderRequest.price())
                .skuCode(orderRequest.skuCode())
                .quantity(orderRequest.quantity())
                .build();
        Order savedOrder = orderRepository.save(order);
        log.info("Placed order successfully for skuCode:{} with order no:{}", orderRequest.skuCode(), savedOrder.getOrderNumber());
        return new OrderResponse(savedOrder.getId(), savedOrder.getOrderNumber(), savedOrder.getSkuCode(), savedOrder.getPrice(), savedOrder.getQuantity());
    }
}
