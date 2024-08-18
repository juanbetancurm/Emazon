package com.pragma.arquetipobootcamp2024.domain.model;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name="category", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory.")
    @Size(max = 50, message = "Name cannot be longer than 50 characters.")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 90, message = "Description cannot be longer than 90 characters")
    @Column(nullable = false, length = 90)
    private String description;

}
