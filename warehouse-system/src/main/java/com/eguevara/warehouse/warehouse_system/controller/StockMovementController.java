package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.StockMovementRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.StockMovementResponseDto;
import com.eguevara.warehouse.warehouse_system.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping
    public Page<StockMovementResponseDto> getAllStockMovements(@PageableDefault(size = 20) Pageable pageable) {
        return stockMovementService.findAllDto(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponseDto> getStockMovementById(@PathVariable Integer id) {
        return stockMovementService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockMovementResponseDto> createStockMovement(@RequestBody StockMovementRequestDto stockMovementRequestDto) {
        StockMovementResponseDto responseDto = stockMovementService.saveDto(stockMovementRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockMovement(@PathVariable Integer id) {
        if (stockMovementService.findByIdDto(id).isPresent()) {
            stockMovementService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}