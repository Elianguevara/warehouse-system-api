package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * Representa la tabla 'Product_Lot'.
 * Esta entidad es crucial para la gestión de productos perecederos.
 * Permite rastrear la fecha de vencimiento y la cantidad de cada lote de un producto.
 */
@Entity
@Table(name = "Product_Lot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lotId;

    private LocalDate expirationDate;
    private Integer availableQuantity;

    // Relación de muchos a uno con Product (un lote pertenece a un producto)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}