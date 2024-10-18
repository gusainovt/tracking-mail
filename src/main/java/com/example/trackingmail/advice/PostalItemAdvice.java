package com.example.trackingmail.advice;

import com.example.trackingmail.exception.PostalItemNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostalItemAdvice {
    @ExceptionHandler(PostalItemNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerException(PostalItemNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorMessage(e.getMessage()));
    }
}
