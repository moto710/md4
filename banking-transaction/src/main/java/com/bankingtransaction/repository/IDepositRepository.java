package com.bankingtransaction.repository;

import com.bankingtransaction.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepositRepository extends JpaRepository<Deposit, Integer> {
}
