package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;
import com.pragma.arquetipobootcamp2024.domain.spi.IBrandPersistencePort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    public BrandAdapter(IBrandRepository brandRepository, IBrandEntityMapper brandEntityMapper){
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }
    @Override
    public BrandModel createBrand(BrandModel brandModel){
        BrandEntity brandEntity= brandEntityMapper.toEntity(brandModel);
        BrandEntity savedEntity = brandRepository.save(brandEntity);
        return brandEntityMapper.toModel(savedEntity);
    }

    @Override
    public Optional<BrandModel> getBrandByName(String name) {
        return brandRepository.findByName(name).map(brandEntityMapper::toModel);
    }

    @Override
    public List<BrandModel> getBrandsWithPagination(int page, int size, String sortby, boolean asc){
        Pageable pageable = PageRequest.of(page, size, asc ? Sort.by(sortby).ascending() : Sort.by(sortby).descending());
        return brandRepository.findAll(pageable)
                .stream()
                .map(brandEntityMapper::toModel)
                .toList();

    }
}
