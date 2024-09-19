package com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request.AddArticleRequest;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IArticleRequestMapper {
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "brandId", source = "brandId", ignore = true)
    ArticleModel toModel(AddArticleRequest addArticleRequest);
}