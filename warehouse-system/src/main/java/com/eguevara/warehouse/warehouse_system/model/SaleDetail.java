package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

/**
 * Representa la tabla 'Sale_Detail'.
 * Esta es una tabla de unión que conecta las ventas con los productos,
 * permitiendo registrar qué productos se vendieron en cada transacción y en qué cantidad.
 */
@Entity
@Table(name = "Sale_Detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailId;

    private Integer quantity;
    private BigDecimal unitPrice;

    // Relación de muchos a uno con Sale (un detalle de venta pertenece a una venta)
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    // Relación de muchos a uno con Product (un detalle de venta se refiere a un producto)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}