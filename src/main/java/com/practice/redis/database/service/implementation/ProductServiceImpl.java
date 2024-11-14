package com.practice.redis.database.service.implementation;

import com.practice.redis.database.domain.request.ProductRequest;
import com.practice.redis.database.domain.response.MessageResponse;
import com.practice.redis.database.domain.response.ProductResponse;
import com.practice.redis.database.repository.ProductRepository;
import com.practice.redis.database.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse editProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public List<ProductResponse> getProducts() {
        return null;
    }

    @Override
    public ProductResponse getProductById(UUID productId) {
        return null;
    }

    @Override
    public MessageResponse deleteProductById(UUID productId) {
        return null;
    }
}
