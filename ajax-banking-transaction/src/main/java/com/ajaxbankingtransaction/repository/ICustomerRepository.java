package com.ajaxbankingtransaction.repository;

import com.ajaxbankingtransaction.model.Customer;
import com.ajaxbankingtransaction.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findCustomersByIdAndDeletedIsFalse(Integer id);
    List<Customer> findAllByDeletedIsFalseAndIdNot(Integer id);

    List<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByIdNot(Integer id);

    @Query("SELECT NEW com.ajaxbankingtransaction.model.dto.CustomerDTO (c.id, " +
                                                                        "c.name, " +
                                                                        "c.email, " +
                                                                        "c.phone, " +
                                                                        "c.balance, " +
                                                                        "c.deleted, " +
                                                                        "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.deleted = FALSE AND c.id = :id"
    )
    Optional<CustomerDTO> findCustomerDTOByIdAndDeletedIsFalse(@Param("id") Integer id);

    @Query("SELECT NEW com.ajaxbankingtransaction.model.dto.CustomerDTO (" +
                                                                        "c.id, " +
                                                                        "c.name, " +
                                                                        "c.email, " +
                                                                        "c.phone, " +
                                                                        "c.balance, " +
                                                                        "c.deleted, " +
                                                                        "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.deleted = FALSE AND c.id <> :id"
    )
    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalseAndIdNot(@Param("id") Integer id);

    @Query("SELECT NEW com.ajaxbankingtransaction.model.dto.CustomerDTO (" +
                                                                        "c.id, " +
                                                                        "c.name, " +
                                                                        "c.email, " +
                                                                        "c.phone, " +
                                                                        "c.balance, " +
                                                                        "c.deleted, " +
                                                                        "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.deleted = FALSE"
    )
    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse();

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance - :transactionAmount " +
            "WHERE c.id = :idCustomer"
    )
    void decreaseBalance(@Param("idCustomer") Integer idCustomer, @Param("transactionAmount") BigDecimal transactionAmount);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance + :transactionAmount " +
            "WHERE c.id = :idCustomer"
    )
    void increaseBalance(@Param("idCustomer") Integer idCustomer, @Param("transactionAmount") BigDecimal transactionAmount);
}
