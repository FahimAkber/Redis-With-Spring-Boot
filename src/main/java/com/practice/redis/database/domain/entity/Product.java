package com.practice.redis.database.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.util.UUID;

@RedisHash
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private UUID name;
    private Company company;
    private BigDecimal price;
}
