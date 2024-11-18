package com.practice.redis.database.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private UUID id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
}
