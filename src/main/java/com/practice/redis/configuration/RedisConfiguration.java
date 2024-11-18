package com.practice.redis.configuration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private Integer redisPort;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.database.index}")
    private Integer databaseIndex;
    @Value("${redis.pool.max-connection}")
    private Integer maxConnection;
    @Value("${redis.pool.idle.max-connection}")
    private Integer poolMaxConnection;
    @Value("${redis.pool.idle.min-connection}")
    private Integer poolMinConnection;

    @Bean
    public RedisConnectionFactory getConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);
        redisStandaloneConfiguration.setDatabase(databaseIndex);

        LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration
                .builder()
                .poolConfig(getGenericObjectPoolConfig())
                .build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);

    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setEnableTransactionSupport(Boolean.TRUE);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    private GenericObjectPoolConfig<Void> getGenericObjectPoolConfig() {
        GenericObjectPoolConfig<Void> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(maxConnection);  //Max Connection
        genericObjectPoolConfig.setMaxIdle(poolMaxConnection);
        genericObjectPoolConfig.setMinIdle(poolMinConnection);

        return genericObjectPoolConfig;
    }
}
