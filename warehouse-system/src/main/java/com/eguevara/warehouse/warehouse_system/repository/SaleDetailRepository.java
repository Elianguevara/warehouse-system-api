package com.eguevara.warehouse.warehouse_system.repository;

import com.eguevara.warehouse.warehouse_system.model.SaleDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad SaleDetail.
 * Gestiona la información detallada de cada producto en una venta.
 */
@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {
      List<SaleDetail> findBySaleSaleId(Integer saleId);
}