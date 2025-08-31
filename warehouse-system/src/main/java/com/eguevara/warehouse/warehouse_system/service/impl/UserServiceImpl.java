package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.RegisterDto;
import com.eguevara.warehouse.warehouse_system.dto.UserRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.UserResponseDto;
import com.eguevara.warehouse.warehouse_system.model.User;
import com.eguevara.warehouse.warehouse_system.repository.UserRepository;
import com.eguevara.warehouse.warehouse_system.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerNewUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    // Implementación del método de paginación
    @Override
    public Page<UserResponseDto> findAllDto(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> modelMapper.map(user, UserResponseDto.class));
    }
    
    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserResponseDto> findByIdDto(Integer id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserResponseDto.class));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto saveDto(UserRequestDto userRequestDto) {
        RegisterDto registerDto = modelMapper.map(userRequestDto, RegisterDto.class);
        User createdUser = this.registerNewUser(registerDto);
        return modelMapper.map(createdUser, UserResponseDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}