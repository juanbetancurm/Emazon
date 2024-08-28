package com.pragma.arquetipobootcamp2024.configuration;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter.ProductAdapter;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter.SupplierAdapter;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.ISupplierEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.ISupplierRepository;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.BrandResponse;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.dto.response.CategoryResponse;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma.arquetipobootcamp2024.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma.arquetipobootcamp2024.domain.api.IBrandServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.ICategoryServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.IProductServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.ISupplierServicePort;
import com.pragma.arquetipobootcamp2024.domain.api.usecase.BrandUseCase;
import com.pragma.arquetipobootcamp2024.domain.api.usecase.CategoryUseCase;
import com.pragma.arquetipobootcamp2024.domain.api.usecase.ProductUseCase;
import com.pragma.arquetipobootcamp2024.domain.api.usecase.SupplierUseCase;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;
import com.pragma.arquetipobootcamp2024.domain.model.CategoryModel;
import com.pragma.arquetipobootcamp2024.domain.spi.IBrandPersistencePort;
import com.pragma.arquetipobootcamp2024.domain.spi.ICategoryPersistencePort;
import com.pragma.arquetipobootcamp2024.domain.spi.IProductPersistencePort;
import com.pragma.arquetipobootcamp2024.domain.spi.ISupplierPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final ISupplierRepository supplierRepository;
    private final ISupplierEntityMapper supplierEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductAdapter(productRepository, productEntityMapper, supplierRepository, supplierEntityMapper);
    }
    @Bean
    public IProductServicePort productServicePort() {

        return new ProductUseCase(productPersistencePort());
    }
    @Bean
    public ISupplierPersistencePort supplierPersistencePort() {
        return new SupplierAdapter(supplierRepository, supplierEntityMapper);
    }
    @Bean
    public ISupplierServicePort supplierServicePort() {

        return new SupplierUseCase(supplierPersistencePort());
    }


    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort(), categoryEntityMapper);
    }

    @Bean
    public ICategoryResponseMapper categoryResponseMapper(){
        return new ICategoryResponseMapper() {
            @Override
            public CategoryResponse toResponse(CategoryModel categoryModel) {
                return new CategoryResponse(
                        categoryModel.getId(),
                        categoryModel.getName(),
                        categoryModel.getDescription()
                );
            }

            @Override
            public List<CategoryResponse> toCategoryResponseList(List<CategoryModel> categoryModels) {
                if (categoryModels == null){
                    return List.of();
                }

                return categoryModels.stream()
                        .map(this::toResponse)
                        .toList();
            }
        };
    }









    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }
    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort(), brandEntityMapper);
    }

    @Bean
    public IBrandResponseMapper brandResponseMapper(){
        return new IBrandResponseMapper() {
            @Override
            public BrandResponse toResponse(BrandModel brandModel) {
                return new BrandResponse(
                        brandModel.getId(),
                        brandModel.getName(),
                        brandModel.getDescription()
                );
            }

            @Override
            public List<BrandResponse> toBrandResponseList(List<BrandModel> brandModels) {
                if (brandModels == null){
                    return List.of();
                }

                return brandModels.stream()
                        .map(this::toResponse)
                        .toList();
            }
        };
    }
}
