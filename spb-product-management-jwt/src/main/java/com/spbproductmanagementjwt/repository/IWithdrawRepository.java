package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWithdrawRepository extends JpaRepository<Withdraw, Long> {
}
