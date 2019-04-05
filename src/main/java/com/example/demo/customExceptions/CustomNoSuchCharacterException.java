package com.example.demo.customExceptions;

public class CustomNoSuchCharacterException extends RuntimeException {
    public CustomNoSuchCharacterException() {
    }

    public CustomNoSuchCharacterException(String message) {
        super(message);
    }
}
