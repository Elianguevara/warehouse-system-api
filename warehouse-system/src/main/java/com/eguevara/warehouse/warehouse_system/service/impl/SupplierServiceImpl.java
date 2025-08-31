package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.SupplierRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.SupplierResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Supplier;
import com.eguevara.warehouse.warehouse_system.repository.SupplierRepository;
import com.eguevara.warehouse.warehouse_system.service.SupplierService;
import com.eguevara.warehouse.warehouse_system.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
    
    // Implementación del método de paginación
    @Override
    public Page<SupplierResponseDto> findAllDto(Pageable pageable) {
        return supplierRepository.findAll(pageable)
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDto.class));
    }

    @Override
    public Optional<Supplier> findById(Integer id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<SupplierResponseDto> findByIdDto(Integer id) {
        return supplierRepository.findById(id)
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDto.class));
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public SupplierResponseDto saveDto(SupplierRequestDto supplierRequestDto) {
        Supplier supplier = modelMapper.map(supplierRequestDto, Supplier.class);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return modelMapper.map(savedSupplier, SupplierResponseDto.class);
    }

    @Override
    public Optional<SupplierResponseDto> updateDto(Integer id, SupplierRequestDto supplierDetailsDto) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setCompanyName(supplierDetailsDto.getCompanyName());
                    supplier.setPhone(supplierDetailsDto.getPhone());
                    supplier.setEmail(supplierDetailsDto.getEmail());
                    Supplier updatedSupplier = supplierRepository.save(supplier);
                    return modelMapper.map(updatedSupplier, SupplierResponseDto.class);
                })
                .map(Optional::of)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        supplierRepository.deleteById(id);
    }
}