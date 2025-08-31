package com.eguevara.warehouse.warehouse_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public  class SupplierRequestDto {
    @NotBlank
    private String companyName;
    
    @NotBlank
    private String phone;
    
    @NotBlank
    @Email
    private String email;
}