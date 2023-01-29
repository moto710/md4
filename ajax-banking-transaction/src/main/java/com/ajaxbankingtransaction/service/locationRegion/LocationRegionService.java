package com.ajaxbankingtransaction.service.locationRegion;

import com.ajaxbankingtransaction.model.LocationRegion;
import com.ajaxbankingtransaction.repository.ILocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LocationRegionService implements ILocationRegionService {

    @Autowired
    private ILocationRegionRepository locationRegionRepository;


    @Override
    public Iterable<LocationRegion> findAll() {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(LocationRegion locationRegion) {
        locationRegionRepository.save(locationRegion);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void remove(LocationRegion locationRegion) {

    }


}
