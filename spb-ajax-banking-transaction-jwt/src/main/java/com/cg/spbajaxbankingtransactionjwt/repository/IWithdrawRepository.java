package com.cg.spbajaxbankingtransactionjwt.repository;

import com.cg.spbajaxbankingtransactionjwt.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWithdrawRepository extends JpaRepository<Withdraw, Integer> {
}
