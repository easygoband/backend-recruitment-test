package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Location;
import com.easy.zssn.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements LocationInterface {
    @Autowired
    public LocationRepository locationService;
    @Override
    public Location upsertLocation(Location newLocation) {
        return locationService.save(newLocation);
    }
    @Override
    public boolean deleteLocation(int id) {
        boolean resultado= false;
        try {
            locationService.deleteById(id);
            resultado = true;
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }
    @Override
    public Location findByIDLocation(int id) {
        return locationService.findById(id).get();
    }
    @Override
    public List<Location> findAllLocation() {
        return locationService.findAll();
    }
}
