package com.practice.redis.common.handler;

import com.practice.redis.common.exception.ApplicationException;
import com.practice.redis.database.domain.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<Object> exceptionResponse(ApplicationException applicationException){
        return new ResponseEntity<>(new MessageResponse(applicationException.getErrorMessage()), applicationException.getErrorCode());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exceptionResponse(MethodArgumentNotValidException exception){
        Map<String, String> errors = exception.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(new MessageResponse(errors.keySet().stream().map(key -> errors.get(key)).toList().toString()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exceptionResponse(Exception applicationException){
        return new ResponseEntity<>(new MessageResponse(applicationException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
