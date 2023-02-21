package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateLocationRequest;
import com.easygo.david.easygotest.models.Location;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.LocationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationsService {
    @Autowired
    private final LocationsRepository locationsRepository;

    public Location registrateLocation(UUID uuid, NewSurvivorRequest request) {
        if (uuid == null || request == null) throw new IllegalStateException("Location can't be null");
        return locationsRepository.save(new Location(uuid, request.getLatitude(), request.getLongitude(), Date.from(Instant.now())));
    }

    public Location updateSurvivorLastLocation(UUID id, UpdateLocationRequest request) {
        if (id == null) throw new IllegalStateException("id can't be null");

        System.out.println(locationsRepository.findById(id));
        var found = locationsRepository.findById(id);
        if (found.isPresent()) {
            Location toUpdate = found.get();
            toUpdate.setLatitude(request.getLatitude());
            toUpdate.setLongitude(request.getLongitude());
            toUpdate.setLast_modified(Date.from(Instant.now()));

            return locationsRepository.save(toUpdate);
        } else
            throw new IllegalStateException("id not found");
    }

    public Location getUserLocation(UUID id) {
        if (id == null) throw new IllegalStateException("id can't be null");

        var found = locationsRepository.findById(id);

        if (found.isPresent())
            return found.get();
        else
            throw new IllegalStateException("id not found");
    }

    public void deleteSurvivorLocation(String id) {
        if (id == null) throw new IllegalStateException("ID can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            locationsRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }
}
