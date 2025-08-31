package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.dto.SupplierRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.SupplierResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface SupplierService extends GenericService<Supplier, Integer> {
    List<Supplier> findAll();
    Optional<Supplier> findById(Integer id);
    Supplier save(Supplier supplier);
    void deleteById(Integer id);

    // Nuevo método para manejar DTOs con paginación
    Page<SupplierResponseDto> findAllDto(Pageable pageable);
    Optional<SupplierResponseDto> findByIdDto(Integer id);
    SupplierResponseDto saveDto(SupplierRequestDto supplierRequestDto);
    Optional<SupplierResponseDto> updateDto(Integer id, SupplierRequestDto supplierDetailsDto);
}