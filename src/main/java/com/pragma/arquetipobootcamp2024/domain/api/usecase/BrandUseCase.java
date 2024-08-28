package com.pragma.arquetipobootcamp2024.domain.api.usecase;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma.arquetipobootcamp2024.domain.api.IBrandServicePort;
import com.pragma.arquetipobootcamp2024.domain.exception.BlankFieldException;
import com.pragma.arquetipobootcamp2024.domain.exception.InvalidPageParameterException;
import com.pragma.arquetipobootcamp2024.domain.exception.NameAlreadyExistsExceptionD;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;
import com.pragma.arquetipobootcamp2024.domain.spi.IBrandPersistencePort;

import java.util.List;
import java.util.Optional;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;
    private final IBrandEntityMapper brandEntityMapper;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort, IBrandEntityMapper brandEntityMapper){
        this.brandPersistencePort = brandPersistencePort;
        this.brandEntityMapper = brandEntityMapper;
    }
    @Override
    public BrandModel createBrand (BrandModel brandModel){
        Optional<BrandModel> existingBrand = brandPersistencePort.getBrandByName(brandModel.getName());
        if (brandModel.getName() == null || brandModel.getName().trim().isEmpty() ||
                brandModel.getDescription() == null || brandModel.getDescription().trim().isEmpty()) {
            throw new BlankFieldException("Field cannot be blank");
        }
        if (existingBrand.isPresent()) {
            throw new NameAlreadyExistsExceptionD(brandModel.getName());
        }

        return brandPersistencePort.createBrand(brandModel);
    }

    public List<BrandModel> getBrandsWithPagination(int page, int size, String sortBy, boolean asc) {

        if (page < 0) {
            throw new InvalidPageParameterException("Page number cannot be negative.");
        }
        if (size <= 0) {
            throw new InvalidPageParameterException("Page size must be greater than zero.");
        }
        if (sortBy == null || sortBy.trim().isEmpty()) {
            throw new InvalidPageParameterException("SortBy field must not be null or empty.");
        }
        return brandPersistencePort.getBrandsWithPagination(page, size, sortBy, asc);
    }
}
