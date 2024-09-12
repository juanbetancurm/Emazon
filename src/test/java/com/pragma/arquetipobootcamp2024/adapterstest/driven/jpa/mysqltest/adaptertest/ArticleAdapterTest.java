package com.pragma.arquetipobootcamp2024.adapterstest.driven.jpa.mysqltest.adaptertest;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter.ArticleAdapter;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.IArticleEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.IArticleRepository;
import com.pragma.arquetipobootcamp2024.domain.model.ArticleModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private IArticleEntityMapper articleEntityMapper;

    @InjectMocks
    private ArticleAdapter articleAdapter;

    private static final Logger logger = LoggerFactory.getLogger(ArticleAdapterTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedArticleModel() {
        // Given
        ArticleModel articleModel = new ArticleModel(1L, "Article Name", "Article Description", 5, 100.5, Set.of());
        ArticleEntity articleEntity = new ArticleEntity();
        ArticleEntity savedArticleEntity = new ArticleEntity();
        ArticleModel expectedArticleModel = new ArticleModel(1L, "Article Name", "Article Description", 5, 100.5, Set.of());

        when(articleEntityMapper.toEntity(articleModel)).thenReturn(articleEntity);
        when(articleRepository.save(articleEntity)).thenReturn(savedArticleEntity);
        when(articleEntityMapper.toModel(savedArticleEntity)).thenReturn(expectedArticleModel);

        // When
        ArticleModel actualArticleModel = articleAdapter.save(articleModel);

        // Then
        logger.info("Expected ArticleModel: {}", expectedArticleModel);
        logger.info("Actual ArticleModel: {}", actualArticleModel);

        assertEquals(expectedArticleModel, actualArticleModel, "The saved ArticleModel should match the expected ArticleModel");
    }

    @Test
    void save_ShouldLogCorrectMessages() {
        // Given
        ArticleModel articleModel = new ArticleModel(1L, "Article Name", "Article Description", 5, 100.5, Set.of());
        ArticleEntity articleEntity = new ArticleEntity();
        ArticleEntity savedArticleEntity = new ArticleEntity();
        ArticleModel expectedArticleModel = new ArticleModel(1L, "Article Name", "Article Description", 5, 100.5, Set.of());

        when(articleEntityMapper.toEntity(articleModel)).thenReturn(articleEntity);
        when(articleRepository.save(articleEntity)).thenReturn(savedArticleEntity);
        when(articleEntityMapper.toModel(savedArticleEntity)).thenReturn(expectedArticleModel);

        // When
        ArticleModel actualArticleModel = articleAdapter.save(articleModel);

        // Then
        verify(articleRepository).save(articleEntity);
        verify(articleEntityMapper).toEntity(articleModel);
        verify(articleEntityMapper).toModel(savedArticleEntity);

        // Log the expected and actual values
        logger.info("Test: save_ShouldLogCorrectMessages");
        logger.info("Expected ArticleModel: {}", expectedArticleModel);
        logger.info("Actual ArticleModel: {}", actualArticleModel);

        assertEquals(expectedArticleModel, actualArticleModel, "The saved ArticleModel should match the expected ArticleModel");
    }
}