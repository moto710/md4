package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Cart;
import com.spbproductmanagementjwt.model.CartDetail;
import com.spbproductmanagementjwt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findAllByCart(Cart cart);


    @Query("SELECT SUM(cd.productAmount) FROM CartDetail AS cd WHERE cd.cart.id = :cartId")
    BigDecimal sumTotalAmountByCartId(@Param("cartId") Long cartId);

    @Query("SELECT SUM(cd.productAmount) FROM CartDetail AS cd WHERE cd.cart = :cart")
    BigDecimal getAllProductAmount(@Param("cart") Cart cart);

    Optional<CartDetail> findByProduct(Product product);
}
