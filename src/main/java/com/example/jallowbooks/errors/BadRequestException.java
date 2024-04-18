package com.example.jallowbooks.errors;

public class BadRequestException  extends RuntimeException  {
    public BadRequestException(String message) {
        super(message);
    }
}
