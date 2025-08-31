package com.eguevara.warehouse.warehouse_system.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductLotResponseDto {
    private Integer lotId;
    private LocalDate expirationDate;
    private Integer availableQuantity;
    private Integer productId;
}