package com.pragma.arquetipobootcamp2024.domain.api.usecase;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.exception.CategoryAlreadyExistExceptionDD;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import com.pragma.arquetipobootcamp2024.domain.spi.ICategoryPersistencePort;

import java.util.List;
import java.util.Optional;


public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, ICategoryEntityMapper categoryEntityMapper){
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryEntityMapper = categoryEntityMapper;
    }
    @Override
    public CategoryModel createCategory (CategoryModel categoryModel){
        Optional<CategoryModel> existingCategory = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistExceptionDD(categoryModel.getName());
        }return categoryPersistencePort.createCategory(categoryModel);
    }

    public List<CategoryModel> getCategoriesWithPagination(int page, int size, String sortBy, boolean asc){
        return categoryPersistencePort.getCategoriesWithPagination(page, size, sortBy, asc);
    }

    @Override
    public List<CategoryModel> getAllCategories(){
        List<CategoryEntity> categories = categoryPersistencePort.getAllCategories();

        return categories.stream()
                .map(categoryEntityMapper::toModel)
                .toList();
    }
    @Override
    public CategoryModel getCategoryById(Long id){
        return categoryPersistencePort.getCategoryById(id);
    }
    public CategoryModel updateCategory(CategoryModel categoryModel){
        return categoryPersistencePort.updateCategory(categoryModel);
    }
    @Override
    public void deleteCategory(Long id){
        categoryPersistencePort.deleteCategory(id);
    }
}
