package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.dto.ProductLotRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductLotResponseDto;
import com.eguevara.warehouse.warehouse_system.model.ProductLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ProductLotService extends GenericService<ProductLot, Integer> {
   // Nuevo método para manejar DTOs con paginación
   Page<ProductLotResponseDto> findAllDto(Pageable pageable);
   Optional<ProductLotResponseDto> findByIdDto(Integer id);
   ProductLotResponseDto saveDto(ProductLotRequestDto productLotRequestDto);
}