package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.SupplierRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.SupplierResponseDto;
import com.eguevara.warehouse.warehouse_system.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public Page<SupplierResponseDto> getAllSuppliers(@PageableDefault(size = 20) Pageable pageable) {
        return supplierService.findAllDto(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> getSupplierById(@PathVariable Integer id) {
        return supplierService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDto> createSupplier(@RequestBody SupplierRequestDto supplierRequestDto) {
        SupplierResponseDto responseDto = supplierService.saveDto(supplierRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> updateSupplier(@PathVariable Integer id, @RequestBody SupplierRequestDto supplierDetailsDto) {
        return supplierService.updateDto(id, supplierDetailsDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        if (supplierService.findByIdDto(id).isPresent()) {
            supplierService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}