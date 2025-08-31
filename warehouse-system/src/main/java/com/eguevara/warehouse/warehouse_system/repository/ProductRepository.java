package com.eguevara.warehouse.warehouse_system.repository;

import com.eguevara.warehouse.warehouse_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Product.
 * Extiende JpaRepository para obtener métodos CRUD listos para usar.
 * Incluye un método personalizado para buscar productos por código de barras.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByBarcode(String barcode);
}