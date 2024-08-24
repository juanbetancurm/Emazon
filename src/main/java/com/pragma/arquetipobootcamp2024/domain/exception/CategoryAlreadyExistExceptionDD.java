package com.pragma.arquetipobootcamp2024.domain.exception;

public class CategoryAlreadyExistExceptionDD extends RuntimeException{
    public CategoryAlreadyExistExceptionDD (String name) {
        super(String.format("Category with name '%s' already exists", name));
    }
}
