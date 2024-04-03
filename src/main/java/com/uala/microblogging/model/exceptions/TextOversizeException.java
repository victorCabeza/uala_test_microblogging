package com.uala.microblogging.model.exceptions;

public class TextOversizeException extends RuntimeException{
    public TextOversizeException(final String message) {
        super(message);
    }
}
