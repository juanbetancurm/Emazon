package com.pragma.arquetipobootcamp2024.domain.api.usecase;


import com.pragma.arquetipobootcamp2024.domain.api.IArticleServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.exception.InvalidCategoryCountException;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;

import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import com.pragma.arquetipobootcamp2024.domain.spi.IArticlePersistencePort;


import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleUseCase implements IArticleServicePort {
    private static final Logger logger = LoggerFactory.getLogger(ArticleUseCase.class);
    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryServicePort categoryServicePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, ICategoryServicePort categoryServicePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryServicePort = categoryServicePort;
    }

    @Override
    public ArticleModel createNewArticle(ArticleModel articleModel) {
        logger.info("Received ArticleModel in ArticleUseCase: {}", articleModel);

        Set<Long> categoryIds = articleModel.getCategoryIds();
        logger.info("Extracted Category IDs: {}", categoryIds);

        if (categoryIds == null || categoryIds.isEmpty() || categoryIds.size() > 3) {
            throw new InvalidCategoryCountException("Article must have between 1 and 3 categories");
        }

        Set<CategoryModel> validCategories = new HashSet<>();
        for (Long categoryId : categoryIds) {
            CategoryModel categoryModel = categoryServicePort.getCategoryById(categoryId);
            validCategories.add(categoryModel);
        }

        logger.info("Categories validated and added: {}", validCategories);
        articleModel.setCategories(validCategories);

        if (validCategories.isEmpty() || validCategories.size() > 3) {
            throw new InvalidCategoryCountException("Article must have between 1 and 3 categories");
        }
        articleModel.setCategories(validCategories);

        ArticleModel savedArticle = articlePersistencePort.save(articleModel);
        logger.info("ArticleModel saved: {}", savedArticle);
        // Save the article
        //return articlePersistencePort.save(articleModel);
        return savedArticle;
    }
}

