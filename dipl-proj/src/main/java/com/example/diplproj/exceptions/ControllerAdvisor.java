package com.example.diplproj.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EnumValueNotAllowedException.class)
    public ResponseEntity<Object> handleEnumValueNotAllowedException(EnumValueNotAllowedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityDoesNotExistException.class)
    public ResponseEntity<Object> handleEntityDoesNotExistException(EntityDoesNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<Object> handleUniqueConstraintException(UniqueConstraintException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleAuthClientException(AuthException ex) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

