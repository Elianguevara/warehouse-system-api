package com.eguevara.warehouse.warehouse_system.repository;

import com.eguevara.warehouse.warehouse_system.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Supplier.
 * Proporciona métodos CRUD para gestionar la información de los proveedores.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}