package com.avidea.sinistreapp.web.rest.exceptions;

public class ClaimNotFoundException extends RuntimeException {

    private final String message;

    public ClaimNotFoundException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
