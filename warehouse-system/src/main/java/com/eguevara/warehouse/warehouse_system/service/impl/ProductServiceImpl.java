package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.dto.ProductRequestDto;
import com.eguevara.warehouse.warehouse_system.dto.ProductResponseDto;
import com.eguevara.warehouse.warehouse_system.model.Category;
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
 * Implementation of the service for the Product entity.
 * Contains the business logic for product operations,
 * such as finding, saving, updating, and deleting.
 */
@Service // Indicates to Spring that this class is a service bean.
public class ProductServiceImpl implements ProductService {

    @Autowired // Injects the repository for database access.
    private ProductRepository productRepository;

    @Autowired
    private SupplierServiceImpl supplierService;
    
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Returns a list of all products in the database.
     * @return A list of Product objects.
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // New paginated method
    @Override
    public Page<ProductResponseDto> findAllDto(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }
    
    /**
     * Finds a product by its ID.
     * @param id The product's ID.
     * @return An Optional that may contain the Product object if found.
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
     * Finds a product by its barcode.
     * It is crucial for the barcode reader functionality.
     * @param barcode The product's barcode.
     * @return The found Product object or null if it doesn't exist.
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
     * Saves or updates a product in the database.
     * @param product The Product object to save.
     * @return The saved Product object with the assigned ID.
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductResponseDto saveDto(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);
        
        // Associates the category if an ID is provided
        if (productRequestDto.getCategoryId() != null) {
            Category category = categoryService.findById(productRequestDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + productRequestDto.getCategoryId()));
            product.setCategory(category);
        }

        // Associates the supplier if an ID is provided
        if (productRequestDto.getSupplierId() != null) {
            Supplier supplier = supplierService.findById(productRequestDto.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with ID: " + productRequestDto.getSupplierId()));
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
            product.setBarcode(productDetailsDto.getBarcode());
            product.setProductType(productDetailsDto.getProductType());

            // Associates the category if an ID is provided
            if (productDetailsDto.getCategoryId() != null) {
                Category category = categoryService.findById(productDetailsDto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + productDetailsDto.getCategoryId()));
                product.setCategory(category);
            }
            
            // Associates the supplier if an ID is provided
            if (productDetailsDto.getSupplierId() != null) {
                Supplier supplier = supplierService.findById(productDetailsDto.getSupplierId())
                        .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with ID: " + productDetailsDto.getSupplierId()));
                product.setSupplier(supplier);
            }

            Product updatedProduct = productRepository.save(product);
            return modelMapper.map(updatedProduct, ProductResponseDto.class);
        });
    }

    /**
     * Deletes a product from the database by its ID.
     * @param id The ID of the product to delete.
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