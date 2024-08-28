package com.pragma.arquetipobootcamp2024.domain.exception;

public class CategoryBlankFieldException extends RuntimeException{
    public CategoryBlankFieldException(String message) {
        super(message);
    }
}
