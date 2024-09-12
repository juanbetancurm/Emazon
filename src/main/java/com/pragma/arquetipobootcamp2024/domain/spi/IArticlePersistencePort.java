package com.pragma.arquetipobootcamp2024.domain.spi;


import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;

public interface IArticlePersistencePort {
    ArticleModel save(ArticleModel articleModel);

}

