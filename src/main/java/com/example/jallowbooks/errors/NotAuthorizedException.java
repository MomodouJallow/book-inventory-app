package com.example.jallowbooks.errors;

public class NotAuthorizedException extends RuntimeException  {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
