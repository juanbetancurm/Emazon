package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class CategoryEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 50, unique = true)
        @NotEmpty(message = "Name is required")
        @Size(max = 50, message = "Name must be less than or equal to 50 characters")
        private String name;

        @Column(nullable = false, length = 90)
        @NotEmpty(message = "Description is required")
        @Size(max = 90, message = "Description must be less than or equal to 90 characters")
        private String description;

}
