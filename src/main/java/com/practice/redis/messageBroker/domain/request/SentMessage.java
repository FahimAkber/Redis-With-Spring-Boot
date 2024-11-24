package com.practice.redis.messageBroker.domain.request;

import lombok.Data;

@Data
public class SentMessage {
    private String projectName;
    private Double projectCost;
}
