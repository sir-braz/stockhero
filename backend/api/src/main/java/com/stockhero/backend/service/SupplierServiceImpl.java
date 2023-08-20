package com.stockhero.backend.service;

import com.stockhero.backend.entity.Supplier;
import com.stockhero.backend.repository.SupplierRepository;
import com.stockhero.backend.utiliy.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        try {
            supplierRepository.save(supplier);
        } catch (BackendException ex) {
            throw new BackendException("Unable to create supplier" + ex);
        }
        return supplier;
    }

    @Override
    public List<Supplier> listSuppliers() {
        return null;
    }

    @Override
    public Supplier findSupplierById(Long supplierId) {
        return null;
    }

    @Override
    public void deleteSupplierById(Long supplierId) {

    }

    @Override
    public Supplier createProduct(Supplier supplier) {
        return null;
    }
}
