package com.eguevara.warehouse.warehouse_system.service.impl;

import com.eguevara.warehouse.warehouse_system.model.SaleDetail;
import com.eguevara.warehouse.warehouse_system.repository.SaleDetailRepository;
import com.eguevara.warehouse.warehouse_system.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Override
    public List<SaleDetail> findAll() {
        return saleDetailRepository.findAll();
    }

    @Override
    public Optional<SaleDetail> findById(Integer id) {
        return saleDetailRepository.findById(id);
    }

    @Override
    public SaleDetail save(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    @Override
    public void deleteById(Integer id) {
        saleDetailRepository.deleteById(id);
    }

    @Override
    public List<SaleDetail> findBySaleSaleId(Integer saleId) {
        return saleDetailRepository.findBySaleSaleId(saleId);
    }
}