package com.eguevara.warehouse.warehouse_system.repository;

import com.eguevara.warehouse.warehouse_system.model.Sale;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Sale.
 * Permite gestionar todas las transacciones de venta.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
      
}