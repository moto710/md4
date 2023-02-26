package com.spbproductmanagementjwt.locationregion;

import com.spbproductmanagementjwt.locationregion.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILocationRegionRepository extends JpaRepository<LocationRegion, Long> {
}
