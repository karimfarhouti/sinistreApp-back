package com.avidea.sinistreapp.web.rest.exceptions;

public class ClaimFileDoesNotExistException extends RuntimeException {
    private final String message;

    public ClaimFileDoesNotExistException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
