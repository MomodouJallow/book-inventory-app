package com.example.jallowbooks.errors;

public class BadRequest extends RuntimeException  {
    public BadRequest(String message) {
        super(message);
    }
}
