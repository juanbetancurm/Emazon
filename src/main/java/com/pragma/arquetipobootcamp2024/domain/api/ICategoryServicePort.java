package com.pragma.arquetipobootcamp2024.domain.api;


import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {

    CategoryModel getCategoryById(Long categoryId);
    CategoryModel createCategory(CategoryModel categoryModel);
    List<CategoryModel> getCategoriesWithPagination(int page, int size, String sortBy, boolean asc);

}
