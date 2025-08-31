package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.StockMovementRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.StockMovementResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Product;
import com.eguevara.warehouse.warehouse_system.model.StockMovement;
import com.eguevara.warehouse.warehouse_system.repository.StockMovementRepository;
import com.eguevara.warehouse.warehouse_system.service.StockMovementService;
import com.eguevara.warehouse.warehouse_system.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StockMovement> findAll() {
        return stockMovementRepository.findAll();
    }
    
    // Implementación del método de paginación
    @Override
    public Page<StockMovementResponseDto> findAllDto(Pageable pageable) {
        return stockMovementRepository.findAll(pageable)
                .map(stockMovement -> modelMapper.map(stockMovement, StockMovementResponseDto.class));
    }

    @Override
    public Optional<StockMovement> findById(Integer id) {
        return stockMovementRepository.findById(id);
    }

    @Override
    public Optional<StockMovementResponseDto> findByIdDto(Integer id) {
        return stockMovementRepository.findById(id)
                .map(stockMovement -> modelMapper.map(stockMovement, StockMovementResponseDto.class));
    }

    @Override
    public StockMovement save(StockMovement stockMovement) {
        return stockMovementRepository.save(stockMovement);
    }

    @Override
    public StockMovementResponseDto saveDto(StockMovementRequestDto stockMovementRequestDto) {
        StockMovement stockMovement = modelMapper.map(stockMovementRequestDto, StockMovement.class);

        // Asocia el producto si se proporciona un ID
        if (stockMovementRequestDto.getProductId() != null) {
            Product product = productService.findById(stockMovementRequestDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + stockMovementRequestDto.getProductId()));
            stockMovement.setProduct(product);
        }

        StockMovement savedStockMovement = stockMovementRepository.save(stockMovement);
        return modelMapper.map(savedStockMovement, StockMovementResponseDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        stockMovementRepository.deleteById(id);
    }
}