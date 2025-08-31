package com.eguevara.warehouse.warehouse_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaleDetailRequestDto {
    @NotNull
    private Integer productId;
    
    @NotNull
    @Positive
    private Integer quantity;
    
    @NotNull
    private BigDecimal unitPrice;
}