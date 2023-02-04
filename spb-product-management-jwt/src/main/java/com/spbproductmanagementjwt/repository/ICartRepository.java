package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

//    Optional<Cart> findByUsername(String username);

    Optional<Cart> findByCreatedBy(String createdBy);
}
