package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.exception;

public class CategoryAlreadyExistException extends RuntimeException{
    public CategoryAlreadyExistException (String name) {
        super(String.format("Category with name '%s' already exists", name));
    }
}
