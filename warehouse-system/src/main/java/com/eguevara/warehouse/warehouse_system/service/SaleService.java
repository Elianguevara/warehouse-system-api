package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.dto.SaleRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.SaleResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface SaleService extends GenericService<Sale, Integer> {
    List<Sale> findAll();
    Optional<Sale> findById(Integer id);
    Sale save(Sale sale);
    void deleteById(Integer id);

    // Nuevo método para manejar DTOs con paginación
    Page<SaleResponseDto> findAllDto(Pageable pageable);
    Optional<SaleResponseDto> findByIdDto(Integer id);
    SaleResponseDto createSaleFromDto(SaleRequestDto saleRequestDto);
}