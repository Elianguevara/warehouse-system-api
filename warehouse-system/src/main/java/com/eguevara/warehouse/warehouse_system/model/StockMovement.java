package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Representa la tabla 'Stock_Movement'.
 * Es fundamental para el control de inventario y la auditoría.
 * Registra cada cambio en el stock que no es una venta, como ajustes por pérdida, daño o entrada de nuevos productos.
 */
@Entity
@Table(name = "Stock_Movement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movementId;

    private String movementType;
    private Integer quantity;
    private LocalDateTime dateTime;
    private String reason;

    // Relación de muchos a uno con Product (un movimiento de stock afecta a un producto)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}