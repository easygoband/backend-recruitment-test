package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.controllers.request.UpdateLocationRequest;
import com.easygo.david.easygotest.models.Location;
import com.easygo.david.easygotest.services.LocationsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/survivors/last-location")
@AllArgsConstructor
public class LocationsController {
    @Autowired
    private final LocationsService locationsService;

    @GetMapping()
    ResponseEntity<List<Location>> getUsersLastLocation() {
        return new ResponseEntity<>(locationsService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping(value = "/{user_id}")
    ResponseEntity<Location> getUserLastLocation(@PathVariable("user_id") String id) {
        return new ResponseEntity<>(locationsService.getUserLocation(UUID.fromString(id)), HttpStatus.OK);
    }

    @PutMapping(value = "/{user_id}")
    ResponseEntity<Location> updateLastLocation(@PathVariable("user_id") String id, @RequestBody UpdateLocationRequest request) {
        return new ResponseEntity<>(locationsService.updateSurvivorLastLocation(UUID.fromString(id),request), HttpStatus.OK);
    }
}
