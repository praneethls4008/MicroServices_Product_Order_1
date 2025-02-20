package com.learn.microservices.product.service;

import com.learn.microservices.product.dto.ProductRequest;
import com.learn.microservices.product.dto.ProductResponse;
import com.learn.microservices.product.model.Product;
import com.learn.microservices.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product
                .builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully!");
        return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice());
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository
                .findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }

}
