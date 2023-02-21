package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.models.Location;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocationsService {
    public Location registrateLocation(UUID survivor_id, NewSurvivorRequest requestBody) {
        return new Location();
    }

    public void deleteSurvivorLocation(String id) {
    }
}
