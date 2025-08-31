package com.eguevara.warehouse.warehouse_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank
    private String name;
}