package com.avidea.sinistreapp.web.rest.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ClaimNumberAlreadyExistsException.class)
    public ResponseEntity<Object> handleClaimNumberAlreadyExistsException(ClaimNumberAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ContractNumberAlreadyExistsException.class)
    public ResponseEntity<Object> handleContractNumberAlreadyExistsException(ContractNumberAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ClaimNotFoundException.class)
    public ResponseEntity<Object> handleClaimNotFoundException(ClaimNotFoundException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<Object> handleEmptyFileException(EmptyFileException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmptyFileNameException.class)
    public ResponseEntity<Object> handleEmptyFileNameException(EmptyFileNameException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(WrongFileExtensionException.class)
    public ResponseEntity<Object> handleWrongFileExtensionException(WrongFileExtensionException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ClaimFileDoesNotExistException.class)
    public ResponseEntity<Object> handleClaimFileDoesNotExistException(ClaimFileDoesNotExistException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
