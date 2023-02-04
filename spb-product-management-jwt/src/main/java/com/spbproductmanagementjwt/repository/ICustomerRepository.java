package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLike(String fullName, String email, String phone);

    Optional<Customer> findByIdAndDeletedIsFalse(Long id);

    List<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByIdNotAndDeletedIsFalse(Long id);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance + :transactionAmount " +
            "WHERE c.id = :customerId")
    void incrementBalance(@Param("customerId") Long customerId, @Param("transactionAmount") BigDecimal transactionAmount);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance - :transactionAmount " +
            "WHERE c.id = :customerId")
    void decrementBalance(@Param("customerId") Long customerId, @Param("transactionAmount") BigDecimal transactionAmount);

    @Modifying
    @Query("UPDATE Customer AS p " +
            "SET p.deleted = true " +
            "WHERE p.id = :customerId"
    )
    void deactivate(@Param("customerId") Long customerId);

    @Modifying
    @Query("UPDATE Customer AS p " +
            "SET p.deleted = false " +
            "WHERE p.id = :customerId"
    )
    void reactivate(@Param("customerId") Long customerId);
}
