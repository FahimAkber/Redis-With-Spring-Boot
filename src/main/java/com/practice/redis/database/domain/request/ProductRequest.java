package com.practice.redis.database.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private UUID id;
    @NotBlank(message = "Product name can't be null")
    private String name;
    @NotNull(message = "Company id can't be null")
    private UUID companyId;
    private BigDecimal price;
}
