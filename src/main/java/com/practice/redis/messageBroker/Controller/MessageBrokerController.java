package com.practice.redis.messageBroker.Controller;

import com.practice.redis.messageBroker.domain.request.SentMessage;
import com.practice.redis.messageBroker.service.MessagePublisher;
import com.practice.redis.messageBroker.service.MessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageBrokerController {

    @Autowired
    private MessagePublisher redisMessagePublisher;

    @Autowired
    private MessageSubscriber redisMessageSubscriber;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody SentMessage sentMessage) {
        redisMessagePublisher.publish(sentMessage);
        return "Message published: " + sentMessage;
    }


    @GetMapping("/messages")
    public List<SentMessage> getMessages() {
        return redisMessageSubscriber.getMessageList();
    }
}
