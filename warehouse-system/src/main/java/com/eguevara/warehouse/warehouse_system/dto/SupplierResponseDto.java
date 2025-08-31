package com.eguevara.warehouse.warehouse_system.dto;

import lombok.Data;

@Data
public class SupplierResponseDto {
    private Integer supplierId;
    private String companyName;
    private String phone;
    private String email;
}