package com.eguevara.warehouse.warehouse_system.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer minStock;
    private String category;
    private String barcode;
    private Integer supplierId;
}