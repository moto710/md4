package com.spbproductmanagementjwt.productmedia;

import com.spbproductmanagementjwt.product.Product;
import com.spbproductmanagementjwt.productmedia.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductMediaRepository extends JpaRepository<ProductMedia, String> {

    ProductMedia findByProduct(Product product);

    Optional<ProductMedia> findById(String id);
}
