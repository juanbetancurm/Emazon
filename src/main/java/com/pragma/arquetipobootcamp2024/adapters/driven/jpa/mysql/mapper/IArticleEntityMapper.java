package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {
    ArticleEntity toEntity(ArticleModel articleModel);
    ArticleModel toModel(ArticleEntity articleEntity);
}
