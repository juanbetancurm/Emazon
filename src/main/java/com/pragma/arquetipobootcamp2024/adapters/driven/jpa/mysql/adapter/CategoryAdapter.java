package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import com.pragma.arquetipobootcamp2024.domain.spi.ICategoryPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper){
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }
    @Override
    public CategoryModel createCategory(CategoryModel categoryModel){
        CategoryEntity categoryEntity= categoryEntityMapper.toEntity(categoryModel);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<CategoryEntity> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel updateCategory(CategoryModel categoryModel) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

}
