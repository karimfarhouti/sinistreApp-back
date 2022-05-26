package com.avidea.sinistreapp.web.rest.exceptions;

public class ClaimNumberAlreadyExistsException extends RuntimeException{

    private final String message;

    public ClaimNumberAlreadyExistsException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
