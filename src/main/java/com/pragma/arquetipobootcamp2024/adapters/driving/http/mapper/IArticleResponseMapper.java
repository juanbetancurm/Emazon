package com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.ArticleResponse;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IArticleResponseMapper {
    ArticleResponse toResponse(ArticleModel articleModel);
}
