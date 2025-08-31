package com.eguevara.warehouse.warehouse_system.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleResponseDto {
    private Integer saleId;
    private LocalDateTime dateTime;
    private BigDecimal totalAmount;
    private String paymentType;
    private List<SaleDetailResponseDto> details;
}