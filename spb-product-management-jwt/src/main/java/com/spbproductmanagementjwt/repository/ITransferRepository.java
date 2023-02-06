package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITransferRepository extends JpaRepository<Transfer, Long> {

}
