package com.pragma.arquetipobootcamp2024.adapters.driving.http.controller;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.CategoryResponse;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    public CategoryRestController(ICategoryServicePort categoryServicePort,
                                  ICategoryEntityMapper categoryEntityMapper,
                                  ICategoryResponseMapper categoryResponseMapper){
        this.categoryServicePort = categoryServicePort;
        this.categoryEntityMapper= categoryEntityMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        try {
            List<CategoryModel> categoryModels = categoryServicePort.getAllCategories();
            List<CategoryResponse> categoryResponses = categoryResponseMapper.toCategoryResponseList(categoryModels);
            return ResponseEntity.ok(categoryResponses);
        }catch (Exception e){
            logger.error("Error retrieving categories", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
