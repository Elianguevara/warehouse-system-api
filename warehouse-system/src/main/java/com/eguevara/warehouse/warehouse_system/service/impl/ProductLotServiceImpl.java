package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.ProductLotRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductLotResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Product;
import com.eguevara.warehouse.warehouse_system.model.ProductType;
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

    // Paginating method implementation
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

        // Associates the product if an ID is provided
        if (productLotRequestDto.getProductId() != null) {
            Product product = productService.findById(productLotRequestDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productLotRequestDto.getProductId()));
            
            // **NEW VALIDATION**: Checks if the product is PERISHABLE
            if (product.getProductType() != ProductType.PERISHABLE) {
                throw new IllegalArgumentException("Cannot create a lot for a non-perishable product.");
            }
            
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