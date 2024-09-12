package com.pragma.arquetipobootcamp2024.adapterstest.driven.jpa.mysqltest.adaptertest.driving.httptest.controller;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.controller.ArticleRestController;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.request.AddArticleRequest;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.ArticleResponse;
import com.pragma.arquetipobootcamp2024.domain.api.IArticleServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IArticleRequestMapper;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IArticleResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ArticleRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleServicePort articleServicePort;

    @MockBean
    private ICategoryServicePort categoryServicePort;

    @MockBean
    private IArticleRequestMapper articleRequestMapper;

    @MockBean
    private IArticleResponseMapper articleResponseMapper;

    private AddArticleRequest addArticleRequest;
    private ArticleModel articleModel;
    private ArticleResponse articleResponse;
    private Set<Long> categoryIds;
    private Set<CategoryModel> categoryModels;

    @BeforeEach
    public void setup() {
        categoryIds = new HashSet<>();
        categoryIds.add(1L);
        categoryIds.add(2L);

        categoryModels = new HashSet<>();
        categoryModels.add(new CategoryModel(1L, "Category1", "Description1"));
        categoryModels.add(new CategoryModel(2L, "Category2", "Description2"));

        addArticleRequest = new AddArticleRequest("Test Article", "Test Description", 10, 100.0, categoryIds);
        articleModel = new ArticleModel(1L, "Test Article", "Test Description", 10, 100.0, categoryModels);
        articleResponse = new ArticleResponse(1L, "Test Article", "Test Description", 10, 100.0, null);

        Mockito.when(articleRequestMapper.toModel(addArticleRequest)).thenReturn(articleModel);
        Mockito.when(articleServicePort.createNewArticle(any(ArticleModel.class))).thenReturn(articleModel);
        Mockito.when(categoryServicePort.getCategoryById(1L)).thenReturn(new CategoryModel(1L, "Category1", "Description1"));
        Mockito.when(categoryServicePort.getCategoryById(2L)).thenReturn(new CategoryModel(2L, "Category2", "Description2"));
        Mockito.when(articleResponseMapper.toResponse(articleModel)).thenReturn(articleResponse);
    }

    @Test
    public void testAddArticle() throws Exception {
        logger.info("Starting testAddArticle...");

        String expectedArticleName = "Test Article";
        String expectedArticleDescription = "Test Description";

        mockMvc.perform(MockMvcRequestBuilders.post("/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Test Article\",\n" +
                                "    \"description\": \"Test Description\",\n" +
                                "    \"quantity\": 10,\n" +
                                "    \"price\": 100.0,\n" +
                                "    \"categoryIds\": [1, 2]\n" +
                                "}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedArticleName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(expectedArticleDescription))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100.0));

        logger.info("Expected article name: {}", expectedArticleName);
        logger.info("Expected article description: {}", expectedArticleDescription);
        logger.info("Test finished for testAddArticle.");
    }

    @Test
    public void testAddArticleWithMissingCategoryIds() throws Exception {
        logger.info("Starting testAddArticleWithMissingCategoryIds...");

        mockMvc.perform(MockMvcRequestBuilders.post("/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Test Article\",\n" +
                                "    \"description\": \"Test Description\",\n" +
                                "    \"quantity\": 10,\n" +
                                "    \"price\": 100.0\n" +
                                "}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        logger.info("Test finished for testAddArticleWithMissingCategoryIds.");
    }
}
