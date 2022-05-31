package com.avidea.sinistreapp.web.rest.exceptions;

public class AuthenticationFailureException extends RuntimeException{
    private final String message;

    public AuthenticationFailureException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
