package com.avidea.sinistreapp.web.rest.exceptions;

public class ContractNumberAlreadyExistsException extends RuntimeException {

    private final String message;

    public ContractNumberAlreadyExistsException(String message) { this.message = message; }

    @Override
    public String getMessage() { return message; }
}
