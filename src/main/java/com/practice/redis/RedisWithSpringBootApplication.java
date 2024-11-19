package com.practice.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisWithSpringBootApplication.class, args);
    }

}
