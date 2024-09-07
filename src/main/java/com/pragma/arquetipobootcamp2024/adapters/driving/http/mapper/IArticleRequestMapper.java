package com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request.AddArticleRequest;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IArticleRequestMapper {
    @Mapping(target = "categories", ignore = true)
    ArticleModel toModel(AddArticleRequest addArticleRequest);
}