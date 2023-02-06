package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndDeletedIsTrue(Long id);
    @Query("SELECT NEW com.spbproductmanagementjwt.model.dto.ProductResponseDTO (" +
                "pm.product.id, " +
                "pm.product.name, " +
                "pm.product.price, " +
                "pm.product.description, " +
                "pm.fileName, " +
                "pm.fileFolder " +
            ") " +
            "FROM ProductMedia AS pm " +
            "WHERE pm.product.deleted = FALSE "
    )
    List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsFalse();

    @Query("SELECT NEW com.spbproductmanagementjwt.model.dto.ProductResponseDTO (" +
            "pm.product.id, " +
            "pm.product.name, " +
            "pm.product.price, " +
            "pm.product.description, " +
            "pm.fileName, " +
            "pm.fileFolder " +
            ") " +
            "FROM ProductMedia AS pm " +
            "WHERE pm.product.deleted = TRUE "
    )
    List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsTrue();

    @Modifying
    @Query("UPDATE Product AS p " +
            "SET p.deleted = true " +
            "WHERE p.id = :productId"
    )
    void deactivate(@Param("productId") Long productId);

    @Modifying
    @Query("UPDATE Product AS p " +
            "SET p.deleted = false " +
            "WHERE p.id = :productId"
    )
    void reactivate(@Param("productId") Long productId);

    @Query("SELECT NEW com.spbproductmanagementjwt.model.dto.ProductResponseDTO (" +
            "pm.product.id, " +
            "pm.product.name, " +
            "pm.product.price, " +
            "pm.product.description, " +
            "pm.fileFolder, " +
            "pm.fileName " +
            ") " +
            "FROM ProductMedia AS pm"
    )
    List<ProductResponseDTO> findAllProductResponseDTO();
}
