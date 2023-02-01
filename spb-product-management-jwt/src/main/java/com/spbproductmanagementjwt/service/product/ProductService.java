package com.spbproductmanagementjwt.service.product;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.repository.IProductRepository;
import com.spbproductmanagementjwt.service.supplier.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISupplierService supplierService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        supplierService.save(product.getSupplier());
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        product.setDeleted(true);
        save(product);
    }
}
