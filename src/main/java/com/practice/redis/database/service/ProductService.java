package com.practice.redis.database.service;

import com.practice.redis.database.domain.request.ProductRequest;
import com.practice.redis.database.domain.response.MessageResponse;
import com.practice.redis.database.domain.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse saveProduct(ProductRequest productRequest);
    ProductResponse editProduct(ProductRequest productRequest);
    List<ProductResponse> getProducts();
    ProductResponse getProductById(UUID productId);
    MessageResponse deleteProductById(UUID productId);
}
