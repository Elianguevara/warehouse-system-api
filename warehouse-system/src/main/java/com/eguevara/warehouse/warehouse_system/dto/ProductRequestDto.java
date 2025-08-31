package com.eguevara.warehouse.warehouse_system.dto;

import com.eguevara.warehouse.warehouse_system.model.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    @NotBlank
    private String name;
    
    @NotBlank
    @Size(max = 255)
    private String description;
    
    @NotNull
    private BigDecimal price;
    
    @NotNull
    private Integer stock;
    
    @NotNull
    private Integer minStock;
    
    @NotBlank
    private String category;
    
    @NotBlank
    private String barcode;

    @NotNull
    private ProductType productType;
    
    private Integer supplierId;
}