package com.pragma.arquetipobootcamp2024.domain.api;


import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {
    CategoryModel createCategory(CategoryModel categoryModel);
    List<CategoryModel> getAllCategories();
    CategoryModel getCategoryById(Long id);
    CategoryModel updateCategory(CategoryModel categoryModel);
    void deleteCategory(Long id);

}
