package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.ProductRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Product;
import com.eguevara.warehouse.warehouse_system.model.Supplier;
import com.eguevara.warehouse.warehouse_system.repository.ProductRepository;
import com.eguevara.warehouse.warehouse_system.service.ProductService;
import com.eguevara.warehouse.warehouse_system.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Implementación del servicio para la entidad Product.
 * Contiene la lógica de negocio para las operaciones con productos,
 * como buscar, guardar, actualizar y eliminar.
 */
@Service // Indica a Spring que esta clase es un bean de servicio.
public class ProductServiceImpl implements ProductService {

    @Autowired // Inyecta el repositorio para el acceso a la base de datos.
    private ProductRepository productRepository;

    @Autowired
    private SupplierServiceImpl supplierService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Devuelve una lista de todos los productos en la base de datos.
     * @return Una lista de objetos Product.
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Nuevo método paginado
    @Override
    public Page<ProductResponseDto> findAllDto(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }
    
    /**
     * Busca un producto por su ID.
     * @param id El ID del producto.
     * @return Un Optional que puede contener el objeto Product si se encuentra.
     */
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<ProductResponseDto> findByIdDto(Integer id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }

    /**
     * Busca un producto por su código de barras.
     * Es crucial para la funcionalidad del lector de códigos de barras.
     * @param barcode El código de barras del producto.
     * @return El objeto Product encontrado o null si no existe.
     */
    @Override
    public Product findByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    @Override
    public Optional<ProductResponseDto> findByBarcodeDto(String barcode) {
        Product product = productRepository.findByBarcode(barcode);
        return Optional.ofNullable(product)
                .map(p -> modelMapper.map(p, ProductResponseDto.class));
    }

    /**
     * Guarda o actualiza un producto en la base de datos.
     * @param product El objeto Product a guardar.
     * @return El objeto Product guardado con el ID asignado.
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductResponseDto saveDto(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);

        // Asocia el proveedor si se proporciona un ID
        if (productRequestDto.getSupplierId() != null) {
            Supplier supplier = supplierService.findById(productRequestDto.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + productRequestDto.getSupplierId()));
            product.setSupplier(supplier);
        }

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }

    @Override
    public Optional<ProductResponseDto> updateDto(Integer id, ProductRequestDto productDetailsDto) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetailsDto.getName());
            product.setDescription(productDetailsDto.getDescription());
            product.setPrice(productDetailsDto.getPrice());
            product.setStock(productDetailsDto.getStock());
            product.setMinStock(productDetailsDto.getMinStock());
            product.setCategory(productDetailsDto.getCategory());
            product.setBarcode(productDetailsDto.getBarcode());

            // Asocia el proveedor si se proporciona un ID
            if (productDetailsDto.getSupplierId() != null) {
                Supplier supplier = supplierService.findById(productDetailsDto.getSupplierId())
                        .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + productDetailsDto.getSupplierId()));
                product.setSupplier(supplier);
            }

            Product updatedProduct = productRepository.save(product);
            return modelMapper.map(updatedProduct, ProductResponseDto.class);
        });
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     * @param id El ID del producto a eliminar.
     */
    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

     @Override
    public List<ProductResponseDto> findLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getStock() <= product.getMinStock())
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }
}