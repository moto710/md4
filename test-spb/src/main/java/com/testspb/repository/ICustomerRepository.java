package com.testspb.repository;

import com.testspb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByDeletedIsFalse();

    @Modifying
    @Query("UPDATE Customer c " +
            "SET c.deleted = TRUE " +
            "WHERE c.id = :id"
    )
    public void deactivate(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Customer c " +
            "SET c.deleted = FALSE " +
            "WHERE c.id = :id"
    )
    public void reactivate(@Param("id") Long id);
}
