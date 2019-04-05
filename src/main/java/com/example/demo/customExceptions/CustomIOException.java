package com.example.demo.customExceptions;

public class CustomIOException extends RuntimeException {
    public CustomIOException() {
    }

    public CustomIOException(String message) {
        super(message);
    }
}
