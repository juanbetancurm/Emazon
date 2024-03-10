package com.pragma.arquetipobootcamp2024.adapters.driven.mapper;

import com.pragma.arquetipobootcamp2024.adapters.driven.entity.SupplierEntity;
import com.pragma.arquetipobootcamp2024.domain.model.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISupplierEntityMapper {
    Supplier toModel(SupplierEntity supplierEntity);
    SupplierEntity toEntity(Supplier supplier);
    List<Supplier> toModelList(List<SupplierEntity> supplierEntities);
}
