package com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AddCategoryRequest {

    @NotBlank(message = "Name is required")
    @Size(max=50, message = "Name must be less than or equal to 50 characters")
    private String name;
    @NotBlank(message = "Description is required")
    @Size(max = 90, message = "Description must be less than or equal to 90 characters")
    private String description;
}
