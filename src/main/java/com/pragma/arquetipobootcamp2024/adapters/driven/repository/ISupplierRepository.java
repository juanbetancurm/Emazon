package com.pragma.arquetipobootcamp2024.adapters.driven.repository;

import com.pragma.arquetipobootcamp2024.adapters.driven.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findByName(String name);
}
