package com.example.demo.customExceptions;

public class NoSuchResultUUIDException extends RuntimeException {
    public NoSuchResultUUIDException(String message) {
        super(message);
    }
}
