package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.dto.RegisterDto;
import com.eguevara.warehouse.warehouse_system.dto.UserRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.UserResponseDto;
import com.eguevara.warehouse.warehouse_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserService extends GenericService<User, Integer> {
    Optional<User> findByUsername(String username);
    User registerNewUser(RegisterDto registerDto);

    // Nuevos métodos para manejar DTOs con paginación
    Page<UserResponseDto> findAllDto(Pageable pageable);
    Optional<UserResponseDto> findByIdDto(Integer id);
    UserResponseDto saveDto(UserRequestDto userRequestDto);
}