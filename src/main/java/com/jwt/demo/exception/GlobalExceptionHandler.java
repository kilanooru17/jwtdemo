package com.jwt.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handle custom exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String> invalidArguments(InvalidArgumentException ex) {
        return new ResponseEntity<>("Invalid Arguments: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<String> unAuthorizedException(Exception ex) {
        return new ResponseEntity<>("UnAuthorized error: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
