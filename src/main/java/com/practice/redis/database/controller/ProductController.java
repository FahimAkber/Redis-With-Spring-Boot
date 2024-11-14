package com.practice.redis.database.controller;

import com.practice.redis.database.domain.request.ProductRequest;
import com.practice.redis.database.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("product/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("save")
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<Object> editProduct(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.editProduct(productRequest), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable("productId") UUID productId) {
        return new ResponseEntity(productService.getProductById(productId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{productId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("productId") UUID productId) {
        return new ResponseEntity<>(productService.deleteProductById(productId), HttpStatus.OK);
    }
}
