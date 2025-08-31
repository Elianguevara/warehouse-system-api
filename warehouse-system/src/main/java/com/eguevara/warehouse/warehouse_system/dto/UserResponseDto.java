// Archivo: src/main/java/com/eguevara/warehouse/warehouse_system/dto/UserResponseDto.java

package com.eguevara.warehouse.warehouse_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
}