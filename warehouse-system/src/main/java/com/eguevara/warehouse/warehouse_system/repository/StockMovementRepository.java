package com.eguevara.warehouse.warehouse_system.repository;

import com.eguevara.warehouse.warehouse_system.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad StockMovement.
 * Crucial para el registro y la auditoría de todos los movimientos de stock.
 */
@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {
}