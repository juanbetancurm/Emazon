package com.pragma.arquetipobootcamp2024.adapters.driving.http.controller;


import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request.AddArticleRequest;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.ArticleResponse;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IArticleRequestMapper;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IArticleResponseMapper;
import com.pragma.arquetipobootcamp2024.configuration.exceptionhandler.ErrorResponse;
import com.pragma.arquetipobootcamp2024.domain.api.IArticleServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.exception.BlankFieldException;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Create a new Article",
            description = "Here you can create a new Article by providing the article's name (max length 50 characters), description (max length 90 characters), quantity, price, and a list of category IDs (between 1 and 3 categories). If an article with the same name already exists or validation fails, an appropriate error will be returned.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Article data to be created",
                    content = @Content(
                            schema = @Schema(implementation = AddArticleRequest.class),
                            examples = @ExampleObject(value = "{ \"name\": \"Test Article\", \"description\": \"Test Description\", \"quantity\": 10, \"price\": 100.0, \"categoryIds\": [1, 2] }")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Article successfully created",
                            content = @Content(
                                    schema = @Schema(implementation = ArticleResponse.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"name\": \"Test Article\", \"description\": \"Test Description\", \"quantity\": 10, \"price\": 100.0, \"categories\": [{ \"id\": 1, \"name\": \"Category 1\" }, { \"id\": 2, \"name\": \"Category 2\" }] }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Validation errors occurred",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(value = "{ \"message\": \"Categories Cannot Be Null\", \"status\": \"400 BAD_REQUEST\", \"timestamp\": \"2024-09-12T16:39:22.6288153\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Category not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(value = "{ \"message\": \"Category not found\", \"status\": \"404 NOT_FOUND\", \"timestamp\": \"2024-09-12T16:39:22.6288153\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "409", description = "Article with the same name already exists",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(value = "{ \"message\": \"Article with the same name already exists\", \"status\": \"409 CONFLICT\", \"timestamp\": \"2024-09-12T16:39:22.6288153\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )

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

