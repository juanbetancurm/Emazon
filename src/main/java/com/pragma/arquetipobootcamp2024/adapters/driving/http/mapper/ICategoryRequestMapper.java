package com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper;


import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request.AddCategoryRequest;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    CategoryModel addRequestToCategoryModel(AddCategoryRequest addCategoryRequest);
}
