package com.avidea.sinistreapp.web.rest.exceptions;

public class WrongFileExtensionException extends RuntimeException {
    private final String message;

    public WrongFileExtensionException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
