package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.ProductLotRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductLotResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Product;
import com.eguevara.warehouse.warehouse_system.model.ProductLot;
import com.eguevara.warehouse.warehouse_system.repository.ProductLotRepository;
import com.eguevara.warehouse.warehouse_system.service.ProductLotService;
import com.eguevara.warehouse.warehouse_system.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductLotServiceImpl implements ProductLotService {

    @Autowired
    private ProductLotRepository productLotRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductLot> findAll() {
        return productLotRepository.findAll();
    }

    // Implementación del método de paginación
    @Override
    public Page<ProductLotResponseDto> findAllDto(Pageable pageable) {
        return productLotRepository.findAll(pageable)
                .map(productLot -> modelMapper.map(productLot, ProductLotResponseDto.class));
    }

    @Override
    public Optional<ProductLot> findById(Integer id) {
        return productLotRepository.findById(id);
    }

    @Override
    public Optional<ProductLotResponseDto> findByIdDto(Integer id) {
        return productLotRepository.findById(id)
                .map(productLot -> modelMapper.map(productLot, ProductLotResponseDto.class));
    }

    @Override
    public ProductLot save(ProductLot productLot) {
        return productLotRepository.save(productLot);
    }

    @Override
    public ProductLotResponseDto saveDto(ProductLotRequestDto productLotRequestDto) {
        ProductLot productLot = modelMapper.map(productLotRequestDto, ProductLot.class);

        // Asocia el producto si se proporciona un ID
        if (productLotRequestDto.getProductId() != null) {
            Product product = productService.findById(productLotRequestDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + productLotRequestDto.getProductId()));
            productLot.setProduct(product);
        }

        ProductLot savedProductLot = productLotRepository.save(productLot);
        return modelMapper.map(savedProductLot, ProductLotResponseDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        productLotRepository.deleteById(id);
    }
}