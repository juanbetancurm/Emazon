package com.pragma.arquetipobootcamp2024.domain.exception;

public class InvalidPageParameterException extends RuntimeException {
    public InvalidPageParameterException(String message) {
        super(message);
    }
}

