package com.eguevara.warehouse.warehouse_system.service;

import com.eguevara.warehouse.warehouse_system.model.SaleDetail;
import java.util.List;
import java.util.Optional;

public interface SaleDetailService extends GenericService<SaleDetail, Integer> {
    
    List<SaleDetail> findBySaleSaleId(Integer saleId);
    
    Optional<SaleDetail> findById(Integer id);
    
    SaleDetail save(SaleDetail saleDetail);
    
    void deleteById(Integer id);
    
    
}