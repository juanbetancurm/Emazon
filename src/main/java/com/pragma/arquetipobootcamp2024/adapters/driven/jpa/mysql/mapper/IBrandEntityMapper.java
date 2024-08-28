package com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toEntity(BrandModel brandModel);
    BrandModel toModel(BrandEntity brandEntity);
}
