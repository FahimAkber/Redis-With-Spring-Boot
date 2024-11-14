package com.practice.redis.database.domain.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    private UUID id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
}
