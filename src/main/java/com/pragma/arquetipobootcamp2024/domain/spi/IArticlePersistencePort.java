package com.pragma.arquetipobootcamp2024.domain.spi;


import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;

import java.util.List;

public interface IArticlePersistencePort {
    ArticleModel save(ArticleModel articleModel);

    List<ArticleModel> listArticles(String sortBy, String sortOrder, int page, int size);

}

