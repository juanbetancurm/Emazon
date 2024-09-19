package com.pragma.arquetipobootcamp2024.domain.api;

import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;

import java.util.List;

public interface IArticleServicePort {
    ArticleModel createNewArticle(ArticleModel articleModel);
    List<ArticleModel> listArticles(String sortBy, String sortOrder, int page, int size);

}
