package com.pragma.arquetipobootcamp2024.domain.spi;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryPersistencePort {
    CategoryModel createCategory (CategoryModel categoryModel);
    CategoryModel getCategoryById(Long id);
    List<CategoryEntity> getAllCategories();
    CategoryModel updateCategory(CategoryModel categoryModel);
    void deleteCategory (Long id);
}
