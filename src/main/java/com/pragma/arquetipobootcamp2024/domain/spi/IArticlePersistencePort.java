package com.pragma.arquetipobootcamp2024.domain.spi;


import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;

import java.util.Optional;

public interface IArticlePersistencePort {
    ArticleModel save(ArticleModel articleModel);

}

