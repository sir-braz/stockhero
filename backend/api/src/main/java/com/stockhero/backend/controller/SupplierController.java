package com.stockhero.backend.controller;

import com.stockhero.backend.entity.Supplier;
import com.stockhero.backend.service.SupplierService;
import com.stockhero.backend.utiliy.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend origin
@RequestMapping("/api") // Defining a base path for all endpoints
public class SupplierController {

    private SupplierService supplierService;

    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(value = "/save-supplier")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier newSupplier = supplierService.createSupplier(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body(newSupplier);
        } catch (BackendException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/list-supplier")
    public ResponseEntity<String> listSupplier() {
        try {
            List<Supplier> suppliers = supplierService.listSuppliers();
            return ResponseEntity.ok().body(suppliers.toString());
        } catch (BackendException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while listing suppliers");
        }
    }
}
