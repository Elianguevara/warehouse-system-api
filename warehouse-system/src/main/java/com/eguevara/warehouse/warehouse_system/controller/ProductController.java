package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.ProductRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductResponseDto;
import com.eguevara.warehouse.warehouse_system.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductResponseDto> getAllProducts(@PageableDefault(size = 20) Pageable pageable) {
        return productService.findAllDto(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Integer id) {
        return productService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<ProductResponseDto> getProductByBarcode(@PathVariable String barcode) {
        return productService.findByBarcodeDto(barcode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

      @GetMapping("/low-stock")
    public List<ProductResponseDto> getLowStockProducts() {
        return productService.findLowStockProducts();
    }
    
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto responseDto = productService.saveDto(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDto productDetailsDto) {
        return productService.updateDto(id, productDetailsDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (productService.findByIdDto(id).isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}