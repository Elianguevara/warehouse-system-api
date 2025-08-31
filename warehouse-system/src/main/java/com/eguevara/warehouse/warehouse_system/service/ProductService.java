package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.dto.ProductRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio para la entidad Producto.
 * Define las operaciones que se pueden realizar sobre los productos,
 * desacoplando la lógica de negocio de la implementación específica.
 */
public interface ProductService extends GenericService<Product, Integer> {

    Product findByBarcode(String barcode);

    // Nuevos métodos para manejar DTOs
    Page<ProductResponseDto> findAllDto(Pageable pageable);
    Optional<ProductResponseDto> findByIdDto(Integer id);
    ProductResponseDto saveDto(ProductRequestDto productRequestDto);
    Optional<ProductResponseDto> updateDto(Integer id, ProductRequestDto productDetailsDto);
    Optional<ProductResponseDto> findByBarcodeDto(String barcode);
    List<ProductResponseDto> findLowStockProducts();
}