package com.pragma.arquetipobootcamp2024.domain.model;
import com.pragma.arquetipobootcamp2024.domain.exception.EmptyFieldException;
import com.pragma.arquetipobootcamp2024.domain.util.DomainConstants;
import lombok.Data;


@Data
public class CategoryModel {

    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (name.trim().length() > 50) {
            throw new IllegalArgumentException("Name cannot exceed 50 characters");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if (description.trim().length() > 90) {
            throw new IllegalArgumentException("Description cannot exceed 90 characters");
        }
        this.id = id;
        this.name = name.trim();
        this.description = description.trim();
    }

}
