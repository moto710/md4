package com.spbproductmanagementjwt.service.supplier;

import com.spbproductmanagementjwt.model.Supplier;
import com.spbproductmanagementjwt.repository.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService{

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return null;
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }
}
