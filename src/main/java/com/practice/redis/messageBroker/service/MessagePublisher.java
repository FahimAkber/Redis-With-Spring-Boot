package com.practice.redis.messageBroker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.redis.messageBroker.domain.request.SentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void publish(SentMessage sentMessage) {
        try {
            String messageJson = objectMapper.writeValueAsString(sentMessage);
            stringRedisTemplate.convertAndSend("my-topic", messageJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
