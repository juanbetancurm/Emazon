package com.pragma.arquetipobootcamp2024.domain.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message) {
        super(message);
    }
}

