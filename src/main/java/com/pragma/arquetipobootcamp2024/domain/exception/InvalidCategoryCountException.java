package com.pragma.arquetipobootcamp2024.domain.exception;

public class InvalidCategoryCountException extends RuntimeException{
    public InvalidCategoryCountException(String message) {
        super(message);
    }
}
