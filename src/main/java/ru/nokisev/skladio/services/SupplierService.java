package ru.nokisev.skladio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nokisev.skladio.models.Supplier;
import ru.nokisev.skladio.repositories.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {

    /**
     * GET /api/suppliers – список поставщиков
     * POST /api/suppliers – добавление поставщика
     * PUT /api/suppliers/{id} – обновление поставщика
     * DELETE /api/suppliers/{id} – удаление поставщика
     * */


    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier addSupplier(Supplier supplier) {

        if (supplierRepository.findByName(supplier.getName()).isEmpty()) {
            return supplierRepository.save(supplier);
        }

        return null;
    }

    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier oldSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setId(id);
        if (supplier.getName() != null) {
            oldSupplier.setName(supplier.getName());
        }
        if (supplier.getContact_name() != null) {
            oldSupplier.setContact_name(supplier.getContact_name());
        }
        if (supplier.getAddress() != null) {
            oldSupplier.setAddress(supplier.getAddress());
        }
        if (supplier.getPhoneNumber() != null) {
            oldSupplier.setPhoneNumber(supplier.getPhoneNumber());
        }
        if (supplier.getProductsTypes() != null) {
            oldSupplier.getProductsTypes().addAll(supplier.getProductsTypes());
        }

        return supplierRepository.save(oldSupplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
