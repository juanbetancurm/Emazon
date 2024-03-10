package com.pragma.arquetipobootcamp2024.adapters.driven.adapter;

import com.pragma.arquetipobootcamp2024.adapters.driven.entity.SupplierEntity;
import com.pragma.arquetipobootcamp2024.adapters.driven.exception.NoDataFoundException;
import com.pragma.arquetipobootcamp2024.adapters.driven.exception.SupplierAlreadyExistsException;
import com.pragma.arquetipobootcamp2024.adapters.driven.mapper.ISupplierEntityMapper;
import com.pragma.arquetipobootcamp2024.adapters.driven.repository.ISupplierRepository;
import com.pragma.arquetipobootcamp2024.domain.model.Supplier;
import com.pragma.arquetipobootcamp2024.domain.spi.ISupplierPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SupplierAdapter implements ISupplierPersistencePort {
    private final ISupplierRepository supplierRepository;
    private final ISupplierEntityMapper supplierEntityMapper;

    @Override
    public void addSupplier(Supplier supplier) {
        if (supplierRepository.findByName(supplier.getName()).isPresent()) {
            throw new SupplierAlreadyExistsException();
        }
        supplierRepository.save(supplierEntityMapper.toEntity(supplier));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<SupplierEntity> suppliers = supplierRepository.findAll();
        if (suppliers.isEmpty()) {
            throw new NoDataFoundException();
        }
        return supplierEntityMapper.toModelList(suppliers);
    }
}
