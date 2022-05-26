package com.avidea.sinistreapp.web.rest.exceptions;

public class EmptyFileException extends RuntimeException {
    private final String message;

    public EmptyFileException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
