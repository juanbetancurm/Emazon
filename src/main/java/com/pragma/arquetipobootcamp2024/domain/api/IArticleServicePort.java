package com.pragma.arquetipobootcamp2024.domain.api;

import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface IArticleServicePort {
    ArticleModel createNewArticle(ArticleModel articleModel);

}
