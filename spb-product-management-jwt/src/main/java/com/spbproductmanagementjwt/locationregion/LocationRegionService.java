package com.spbproductmanagementjwt.locationregion;

import com.spbproductmanagementjwt.locationregion.ILocationRegionService;
import com.spbproductmanagementjwt.locationregion.LocationRegion;
import com.spbproductmanagementjwt.locationregion.ILocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class LocationRegionService implements ILocationRegionService {

    @Autowired
    private ILocationRegionRepository locationRegionRepository;

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }

    @Override
    public List<LocationRegion> findAll() {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return locationRegionRepository.findById(id);
    }

    @Override
    public void save(LocationRegion locationRegion) {
        locationRegionRepository.save(locationRegion);
    }
}
