package com.example.diplproj.exceptions;

public class UniqueConstraintException extends RuntimeException{
    public UniqueConstraintException(String msg) {
        super(msg);
    }
}
