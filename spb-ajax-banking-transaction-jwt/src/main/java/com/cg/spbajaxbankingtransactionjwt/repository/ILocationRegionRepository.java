package com.cg.spbajaxbankingtransactionjwt.repository;

import com.cg.spbajaxbankingtransactionjwt.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRegionRepository extends JpaRepository<LocationRegion, Integer> {
}
