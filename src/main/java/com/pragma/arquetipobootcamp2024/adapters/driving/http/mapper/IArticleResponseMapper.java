package com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.ArticleResponse;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.BrandResponse;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleResponseMapper {
    @Mapping(target = "brandId", source = "brandId")
    ArticleResponse toResponse(ArticleModel articleModel);
    List<ArticleResponse> toArticleResponseList(List<ArticleModel> articleModels);
}
