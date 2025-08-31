package com.eguevara.warehouse.warehouse_system.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaleDetailResponseDto {
    private Integer detailId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Integer productId;
}