package com.example.diplproj.exceptions;

public class EntityDoesNotExistException extends RuntimeException{
    public EntityDoesNotExistException(String msg) {
        super(msg);
    }
}
