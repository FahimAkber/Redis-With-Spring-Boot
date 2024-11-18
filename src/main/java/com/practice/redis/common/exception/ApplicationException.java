package com.practice.redis.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationException extends RuntimeException{
    private String errorMessage;
    private HttpStatusCode errorCode;
}
