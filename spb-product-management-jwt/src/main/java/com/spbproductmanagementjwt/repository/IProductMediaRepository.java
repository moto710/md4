package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductMediaRepository extends JpaRepository<ProductMedia, String> {

    ProductMedia findByProduct(Product product);

    Optional<ProductMedia> findById(String id);
}
