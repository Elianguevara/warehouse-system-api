package com.eguevara.warehouse.warehouse_system.repository;

import com.eguevara.warehouse.warehouse_system.model.ProductLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad ProductLot.
 * Permite gestionar los lotes de productos, especialmente para el control de vencimiento.
 */
@Repository
public interface ProductLotRepository extends JpaRepository<ProductLot, Integer> {
}