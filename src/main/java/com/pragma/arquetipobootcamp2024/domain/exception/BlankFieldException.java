package com.pragma.arquetipobootcamp2024.domain.exception;

public class BlankFieldException extends RuntimeException{
    public BlankFieldException(String message) {
        super(message);
    }
}
