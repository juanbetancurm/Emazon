package com.pragma.arquetipobootcamp2024.domain.model;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;

import com.pragma.arquetipobootcamp2024.domain.exception.BlankFieldException;
import com.pragma.arquetipobootcamp2024.domain.exception.DuplicateCategoryException;
import com.pragma.arquetipobootcamp2024.domain.exception.InvalidCategoryCountException;
import com.pragma.arquetipobootcamp2024.domain.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleModel {
    private static final Logger logger = LoggerFactory.getLogger(ArticleModel.class);
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Set<CategoryModel> categories = new HashSet<>();

    public ArticleModel() {
    }

    public ArticleModel(Long id, String name, String description, int quantity, double price, Set<CategoryModel> categories) {

        logger.info("Received categories: {}", categories);
        this.id = id;
        this.name = name.trim();
        this.description = description.trim();
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        logger.info("Received categories: {}", categories);
    }

    private boolean hasDuplicateCategories(Set<CategoryModel> categories) {
        logger.info("hasDuplicateCategories called with categories: {}", categories);
        if (categories == null) {
            throw new InvalidParameterException("Categories cannot be null");
        }
        return categories.size() != new HashSet<>(categories).size();
    }
    public Set<Long> getCategoryIds() {
        if (categories == null) {
            return Collections.emptySet();
        }
        return categories.stream()
                .map(CategoryModel::getId)
                .collect(Collectors.toSet());
    }
    @Override
    public String toString() {
        return "ArticleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new BlankFieldException("Name cannot be blank");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if (description == null || description.trim().isEmpty()) {
            throw new BlankFieldException("Description cannot be blank");
        }
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryModel> categories) {

        if (categories == null || categories.size() < 1 || categories.size() > 3) {
            throw new InvalidCategoryCountException("Article must have between 1 and 3 categories");
        }

        if (hasDuplicateCategories(categories)) {
            throw new DuplicateCategoryException("Article cannot have duplicate categories");
        }
        this.categories = categories;
    }
}
