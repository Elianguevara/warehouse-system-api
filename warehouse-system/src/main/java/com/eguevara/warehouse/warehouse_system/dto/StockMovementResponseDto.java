package com.eguevara.warehouse.warehouse_system.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StockMovementResponseDto {
    private Integer movementId;
    private String movementType;
    private Integer quantity;
    private LocalDateTime dateTime;
    private String reason;
    private Integer productId;
}