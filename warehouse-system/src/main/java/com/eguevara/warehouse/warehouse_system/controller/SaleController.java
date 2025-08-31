package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.SaleRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.SaleResponseDto;
import com.eguevara.warehouse.warehouse_system.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Endpoint para obtener todas las ventas con paginación
    @GetMapping
    public Page<SaleResponseDto> getAllSales(@PageableDefault(size = 20) Pageable pageable) {
        return saleService.findAllDto(pageable);
    }

    // Endpoint para procesar una nueva venta
    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto responseDto = saleService.createSaleFromDto(saleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Integer id) {
        return saleService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer id) {
        if (saleService.findByIdDto(id).isPresent()) {
            saleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}