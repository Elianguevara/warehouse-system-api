package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa la tabla 'Sale' en la base de datos.
 * Registra cada transacción de venta completa que se realiza en el sistema.
 * Contiene una lista de SaleDetail para modelar la relación uno-a-muchos.
 */
@Entity
@Table(name = "Sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;

    private LocalDateTime dateTime;
    private BigDecimal totalAmount;
    private String paymentType;

    // Relación de uno a muchos con SaleDetail
    // El 'mappedBy' indica que el campo 'sale' en la clase SaleDetail es el propietario de la relación.
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> details;

    // Nota: Gracias a la anotación @Data de Lombok, los métodos getDetails() y setDetails()
    // se generan automáticamente, lo que resuelve el problema que mencionaste en el controlador.
}