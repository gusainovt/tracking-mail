package com.example.trackingmail.exception;

public class PostalItemNotFoundException extends RuntimeException{
    public PostalItemNotFoundException(String message) {
        super(message);
    }
}
