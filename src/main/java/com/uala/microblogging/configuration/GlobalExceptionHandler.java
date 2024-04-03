package com.uala.microblogging.configuration;

import com.uala.microblogging.model.exceptions.TextOversizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, TextOversizeException.class})
    public ResponseEntity<String> badRequestHandler(final RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    }
}