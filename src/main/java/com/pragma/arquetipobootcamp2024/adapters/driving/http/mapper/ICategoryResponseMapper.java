package com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.CategoryResponse;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {


    CategoryResponse toResponse(CategoryModel categoryModel);
    List<CategoryResponse> toCategoryResponseList(List<CategoryModel> categoryModels);
}
