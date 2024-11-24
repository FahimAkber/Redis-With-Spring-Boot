package com.practice.redis.messageBroker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.redis.messageBroker.domain.request.SentMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSubscriber implements MessageListener {

    private List<SentMessage> messageList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String messageBody = new String(message.getBody());
            SentMessage receivedMessage = objectMapper.readValue(messageBody, SentMessage.class);
            messageList.add(receivedMessage);
            System.out.println("Received message: " + receivedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SentMessage> getMessageList() {
        return messageList;
    }
}

