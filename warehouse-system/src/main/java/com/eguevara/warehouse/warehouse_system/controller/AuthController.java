package com.eguevara.warehouse.warehouse_system.controller;

import com.eguevara.warehouse.warehouse_system.dto.RegisterDto;
import com.eguevara.warehouse.warehouse_system.model.User;
import com.eguevara.warehouse.warehouse_system.config.JwtTokenProvider;
import com.eguevara.warehouse.warehouse_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserService userService;

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(jwt);
    }

    // Endpoint de registro (corregido)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        // Verifica si el nombre de usuario ya existe a través de la capa de servicio
        if (userService.findByUsername(registerDto.getUsername()).isPresent()) {
            return new ResponseEntity<>("El nombre de usuario ya existe.", HttpStatus.BAD_REQUEST);
        }

        // Delega la lógica de negocio al servicio
        userService.registerNewUser(registerDto);
        return new ResponseEntity<>("Usuario registrado exitosamente.", HttpStatus.OK);
    }
}