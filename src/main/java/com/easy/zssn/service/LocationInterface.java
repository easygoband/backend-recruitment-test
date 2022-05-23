package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Location;

public interface LocationInterface {
    public Location upsertLocation(Location newLocation);

    public boolean deleteLocation(int id);

    public Location findByIDLocation(int id);

    public List<Location> findAllLocation();
}
