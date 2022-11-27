package com.example.exception;

public class ItemnotFoundException extends RuntimeException{
    public ItemnotFoundException(String message) {
        super(message);
    }
}
