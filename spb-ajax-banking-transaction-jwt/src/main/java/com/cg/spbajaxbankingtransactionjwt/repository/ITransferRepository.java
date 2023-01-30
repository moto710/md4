package com.cg.spbajaxbankingtransactionjwt.repository;

import com.cg.spbajaxbankingtransactionjwt.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITransferRepository extends JpaRepository<Transfer, Integer> {

}
