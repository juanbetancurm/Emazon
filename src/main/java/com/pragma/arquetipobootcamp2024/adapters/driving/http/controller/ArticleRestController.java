package com.pragma.arquetipobootcamp2024.adapters.driving.http.controller;


import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request.AddArticleRequest;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.ArticleResponse;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IArticleRequestMapper;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IArticleResponseMapper;
import com.pragma.arquetipobootcamp2024.domain.api.IArticleServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.exception.BlankFieldException;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleRestController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleRestController.class);
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    public ArticleRestController(IArticleServicePort articleServicePort,
                                 IArticleRequestMapper articleRequestMapper,
                                 IArticleResponseMapper articleResponseMapper,
                                 ICategoryServicePort categoryServicePort) {
        this.articleServicePort = articleServicePort;
        this.articleRequestMapper = articleRequestMapper;
        this.articleResponseMapper = articleResponseMapper;
        this.categoryServicePort = categoryServicePort;
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest addArticleRequest) {

        logger.info("Received AddArticleRequest: {}", addArticleRequest);
        if (addArticleRequest.getCategoryIds() == null) {
            throw new BlankFieldException("Categories Cannot Be Null");
        }
        logger.info("Category IDs: {}", addArticleRequest.getCategoryIds());
        ArticleModel articleModel = articleRequestMapper.toModel(addArticleRequest);

        logger.info("Received articleModel categories in Controller: {}", articleModel.getCategories());

        Set<CategoryModel> categoryModels = addArticleRequest.getCategoryIds().stream()
                .map(categoryServicePort::getCategoryById)
                .collect(Collectors.toSet());


        articleModel.setCategories(categoryModels);


        ArticleModel createdArticle = articleServicePort.createNewArticle(articleModel);
        logger.info("Received articleModel: {}", createdArticle);

        ArticleResponse response = articleResponseMapper.toResponse(createdArticle);
        logger.info("Received articleModel: {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

