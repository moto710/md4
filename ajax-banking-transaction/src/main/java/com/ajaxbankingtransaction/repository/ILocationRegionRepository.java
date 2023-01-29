package com.ajaxbankingtransaction.repository;

import com.ajaxbankingtransaction.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRegionRepository extends JpaRepository<LocationRegion, Integer> {
}
