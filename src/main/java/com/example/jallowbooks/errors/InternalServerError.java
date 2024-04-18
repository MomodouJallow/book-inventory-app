package com.example.jallowbooks.errors;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
