package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByName(String name);
}
