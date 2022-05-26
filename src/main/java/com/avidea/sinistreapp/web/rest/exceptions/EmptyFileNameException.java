package com.avidea.sinistreapp.web.rest.exceptions;

public class EmptyFileNameException extends RuntimeException{
    private final String message;

    public EmptyFileNameException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
