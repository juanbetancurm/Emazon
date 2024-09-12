package com.pragma.arquetipobootcamp2024.domain.api;

import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;

public interface IArticleServicePort {
    ArticleModel createNewArticle(ArticleModel articleModel);

}
