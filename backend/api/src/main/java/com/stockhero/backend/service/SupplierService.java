package com.stockhero.backend.service;

import com.stockhero.backend.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier);

    List<Supplier> listSuppliers();

    Supplier findSupplierById(Long supplierId);

    void deleteSupplierById(Long supplierId);

    Supplier createProduct(Supplier supplier);
}
