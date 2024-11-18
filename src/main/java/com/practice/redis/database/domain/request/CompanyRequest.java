package com.practice.redis.database.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private UUID id;
    @NotBlank(message = "Name can't be null")
    private String name;
    @NotBlank(message = "Address can't be null")
    private String address;
    @NotBlank(message = "Email can't be null")
    private String email;
    @NotBlank(message = "Phone number can't be null")
    private String phoneNumber;
}
