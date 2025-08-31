package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Representa la tabla 'Supplier' en la base de datos.
 * Contiene la información de los proveedores que suministran los productos al almacén.
 * Se relaciona con la clase Producto.
 */
@Entity
@Table(name = "Supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    private String companyName;
    private String phone;
    private String email;
}