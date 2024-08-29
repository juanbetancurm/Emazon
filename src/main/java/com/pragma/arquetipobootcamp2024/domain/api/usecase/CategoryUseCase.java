package com.pragma.arquetipobootcamp2024.domain.api.usecase;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.exception.BlankFieldException;
import com.pragma.arquetipobootcamp2024.domain.exception.InvalidPageParameterException;
import com.pragma.arquetipobootcamp2024.domain.exception.NameAlreadyExistsExceptionD;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import com.pragma.arquetipobootcamp2024.domain.spi.ICategoryPersistencePort;

import java.util.List;
import java.util.Optional;


public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, ICategoryEntityMapper categoryEntityMapper){
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public CategoryModel createCategory (CategoryModel categoryModel){
        Optional<CategoryModel> existingCategory = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (categoryModel.getName() == null || categoryModel.getName().trim().isEmpty() ||
                categoryModel.getDescription() == null || categoryModel.getDescription().trim().isEmpty()) {
            throw new BlankFieldException("Field cannot be blank");
        }
        if (existingCategory.isPresent()) {
            throw new NameAlreadyExistsExceptionD(categoryModel.getName());
        }

        return categoryPersistencePort.createCategory(categoryModel);
    }

    public List<CategoryModel> getCategoriesWithPagination(int page, int size, String sortBy, boolean asc) {

        if (page < 0) {
            throw new InvalidPageParameterException("Page number cannot be negative.");
        }
        if (size <= 0) {
            throw new InvalidPageParameterException("Page size must be greater than zero.");
        }
        if (sortBy == null || sortBy.trim().isEmpty()) {
            throw new InvalidPageParameterException("SortBy field must not be null or empty.");
        }
        return categoryPersistencePort.getCategoriesWithPagination(page, size, sortBy, asc);
    }

}
