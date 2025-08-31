package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

/**
 * Represents the 'Product' table in the database.
 * This is the main entity that stores the details of each item sold or stored.
 * Includes the 'barcode' field for barcode reader lookup.
 * The 'minStock' field is essential for replenishment alerts.
 */
@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer minStock;
    private String barcode;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    // Many-to-one relationship with Category (a product has one category)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Many-to-one relationship with Supplier (a product has one supplier)
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}