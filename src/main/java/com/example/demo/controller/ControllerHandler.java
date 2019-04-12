package com.example.demo.controller;

import com.example.demo.customExceptions.CustomIOException;
import com.example.demo.customExceptions.CustomNoSuchCharacterException;
import com.example.demo.customExceptions.EntityNotFoundException;
import com.example.demo.utils.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(CustomNoSuchCharacterException.class)
    public ResponseEntity<ExceptionMessage> noSuchElement(CustomNoSuchCharacterException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessage(e.getMessage()));
    }
    @ExceptionHandler(CustomIOException.class)
    public ResponseEntity<ExceptionMessage> IoException(CustomIOException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessage(e.getMessage()));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionMessage> entityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessage(e.getMessage()));
    }

}