package com.ajaxbankingtransaction.repository;

import com.ajaxbankingtransaction.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends JpaRepository<Transfer, Integer> {
}
