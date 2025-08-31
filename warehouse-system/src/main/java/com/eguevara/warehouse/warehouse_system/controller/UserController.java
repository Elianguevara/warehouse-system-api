package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.UserRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.UserResponseDto;
import com.eguevara.warehouse.warehouse_system.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponseDto> getAllUsers(@PageableDefault(size = 20) Pageable pageable) {
        return userService.findAllDto(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        return userService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.saveDto(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
}