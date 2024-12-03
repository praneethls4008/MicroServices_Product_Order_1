package com.learn.microservices.order.service;

import com.learn.microservices.order.dto.OrderRequest;
import com.learn.microservices.order.dto.OrderResponse;
import com.learn.microservices.order.model.Order;
import com.learn.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest){
        Order order = Order
                .builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(orderRequest.price())
                .skuCode(orderRequest.skuCode())
                .quantity(orderRequest.quantity())
                .build();
        Order savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder.getId(), savedOrder.getOrderNumber(), savedOrder.getSkuCode(), savedOrder.getPrice(), savedOrder.getQuantity());
    }
}
