package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.dto.StockMovementRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.StockMovementResponseDto;
import com.eguevara.warehouse.warehouse_system.model.StockMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface StockMovementService extends GenericService<StockMovement, Integer> {
    // Nuevos métodos para gestionar DTOs con paginación
    Page<StockMovementResponseDto> findAllDto(Pageable pageable);
    Optional<StockMovementResponseDto> findByIdDto(Integer id);
    StockMovementResponseDto saveDto(StockMovementRequestDto stockMovementRequestDto);
}