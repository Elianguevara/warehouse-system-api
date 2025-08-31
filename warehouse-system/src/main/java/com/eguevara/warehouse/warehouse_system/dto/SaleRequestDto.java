package com.eguevara.warehouse.warehouse_system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class SaleRequestDto {
    @NotBlank
    private String paymentType;
    
    @NotEmpty
    @Valid // Esta anotación es crucial para validar cada objeto dentro de la lista
    private List<SaleDetailRequestDto> details;
}