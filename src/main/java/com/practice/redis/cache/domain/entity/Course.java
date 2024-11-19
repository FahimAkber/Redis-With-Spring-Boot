package com.practice.redis.cache.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private Integer id;
    private String name;
    private Integer credit;
}
