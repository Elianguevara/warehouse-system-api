package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.SaleRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.SaleResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Product;
import com.eguevara.warehouse.warehouse_system.model.Sale;
import com.eguevara.warehouse.warehouse_system.model.SaleDetail;
import com.eguevara.warehouse.warehouse_system.repository.SaleRepository;
import com.eguevara.warehouse.warehouse_system.service.SaleService;
import com.eguevara.warehouse.warehouse_system.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private SaleDetailServiceImpl saleDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    // Implementación del método de paginación
    @Override
    public Page<SaleResponseDto> findAllDto(Pageable pageable) {
        return saleRepository.findAll(pageable)
                .map(sale -> modelMapper.map(sale, SaleResponseDto.class));
    }
    
    @Override
    public Optional<Sale> findById(Integer id) {
        return saleRepository.findById(id);
    }

    @Override
    public Optional<SaleResponseDto> findByIdDto(Integer id) {
        return saleRepository.findById(id)
                .map(sale -> modelMapper.map(sale, SaleResponseDto.class));
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    @Transactional
    public SaleResponseDto createSaleFromDto(SaleRequestDto saleRequestDto) {
        // Mapea el DTO a la entidad de Venta
        Sale sale = modelMapper.map(saleRequestDto, Sale.class);
        sale.setDateTime(LocalDateTime.now());

        // Guarda la entidad de Venta para obtener el saleId
        Sale savedSale = saleRepository.save(sale);

        // Procesa y guarda los detalles de la venta
        saleRequestDto.getDetails().forEach(detailDto -> {
            // Busca el producto por su ID
            Product product = productService.findById(detailDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + detailDto.getProductId()));

            // Mapea el DTO de detalle a la entidad de detalle
            SaleDetail saleDetail = modelMapper.map(detailDto, SaleDetail.class);
            saleDetail.setSale(savedSale); // Asocia el detalle con la venta guardada
            saleDetail.setProduct(product); // Asocia el detalle con el producto

            // Guarda el detalle de la venta
            saleDetailService.save(saleDetail);

            // Actualiza el stock del producto
            product.setStock(product.getStock() - saleDetail.getQuantity());
            productService.save(product);
        });

        // Mapea la entidad de respuesta a un DTO de respuesta
        return modelMapper.map(savedSale, SaleResponseDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        saleRepository.deleteById(id);
    }
}