package com.example.jallowbooks.errors;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}