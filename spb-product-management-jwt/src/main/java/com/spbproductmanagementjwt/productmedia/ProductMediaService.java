package com.spbproductmanagementjwt.productmedia;

import com.spbproductmanagementjwt.product.Product;
import com.spbproductmanagementjwt.productmedia.IProductMediaService;
import com.spbproductmanagementjwt.productmedia.ProductMedia;
import com.spbproductmanagementjwt.productmedia.IProductMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductMediaService implements IProductMediaService {

    @Autowired
    private IProductMediaRepository productMediaRepository;

    @Override
    public List<ProductMedia> findAll() {
        return productMediaRepository.findAll();
    }

    @Override
    public Optional<ProductMedia> findById(Long id) {
        return productMediaRepository.findById(String.valueOf(id));
    }


    @Override
    public ProductMedia findByProduct(Product product) {
        return productMediaRepository.findByProduct(product);
    }

    @Override
    public void save(ProductMedia productMedia) {
        productMediaRepository.save(productMedia);
    }

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }
}
