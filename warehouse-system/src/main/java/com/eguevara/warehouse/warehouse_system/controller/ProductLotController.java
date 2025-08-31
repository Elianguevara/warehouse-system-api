package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.ProductLotRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductLotResponseDto;
import com.eguevara.warehouse.warehouse_system.service.ProductLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-lots")
public class ProductLotController {

    @Autowired
    private ProductLotService productLotService;

    @GetMapping
    public Page<ProductLotResponseDto> getAllProductLots(@PageableDefault(size = 20) Pageable pageable) {
        return productLotService.findAllDto(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductLotResponseDto> getProductLotById(@PathVariable Integer id) {
        return productLotService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductLotResponseDto> createProductLot(@RequestBody ProductLotRequestDto productLotRequestDto) {
        ProductLotResponseDto responseDto = productLotService.saveDto(productLotRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductLot(@PathVariable Integer id) {
        if (productLotService.findByIdDto(id).isPresent()) {
            productLotService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}