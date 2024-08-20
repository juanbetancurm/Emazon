package com.pragma.arquetipobootcamp2024.domain.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CategoryModel {

    private Long id;
    private String name;
    private String description;

}
