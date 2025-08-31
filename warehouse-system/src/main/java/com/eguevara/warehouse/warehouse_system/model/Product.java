package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

/**
 * Representa la tabla 'Product' en la base de datos.
 * Es la entidad principal que almacena los detalles de cada artículo que se vende o almacena.
 * Incluye el campo 'barcode' para la búsqueda por lector de códigos de barras.
 * El campo 'minStock' es esencial para las alertas de reabastecimiento.
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
    private String category;
    private String barcode;

    // Relación de muchos a uno con Supplier (un producto tiene un proveedor)
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}