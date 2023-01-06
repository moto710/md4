package com.bankingtransaction.repository;

import com.bankingtransaction.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByIdNot(int id);
    @Modifying
    @Query("UPDATE Customer AS c SET c.balance = c.balance - :transactionAmount WHERE c.id = :idCustomer")
    void decreaseBalance(@Param("idCustomer") int idCustomer, @Param("transactionAmount") BigDecimal transactionAmount);

    @Modifying
    @Query("UPDATE Customer AS c SET c.balance = c.balance + :transactionAmount WHERE c.id = :idCustomer")
    void increaseBalance(@Param("idCustomer") int idCustomer, @Param("transactionAmount") BigDecimal transactionAmount);
}
