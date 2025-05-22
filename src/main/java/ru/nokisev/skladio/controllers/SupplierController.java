package ru.nokisev.skladio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nokisev.skladio.models.Supplier;
import ru.nokisev.skladio.services.SupplierService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin("*")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> showAllSuppliers() {
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        if (suppliers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(suppliers);
    }


    @PostMapping
    public ResponseEntity<Optional<Supplier>> addNewSupplier(@RequestBody Supplier supplier) {
        supplier = supplierService.addSupplier(supplier);
        if (supplier == null) {
            return new ResponseEntity("Supplier with this name already in system", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(supplier, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplierById(@PathVariable Long id, @RequestBody Supplier supplier) {
        try {
            Supplier updateSupplier = supplierService.updateSupplier(id, supplier);
            return ResponseEntity.ok(supplier);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
