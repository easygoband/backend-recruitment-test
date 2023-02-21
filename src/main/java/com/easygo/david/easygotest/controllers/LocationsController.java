package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.controllers.request.UpdateLocationRequest;
import com.easygo.david.easygotest.models.Location;
import com.easygo.david.easygotest.services.LocationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/survivors/last-location")
@Tag(name = "Survivor Location Controller", description = "This Controller contains the endpoint for getting the information about Survivor's last location on earth.")
@AllArgsConstructor
public class LocationsController {
    @Autowired
    private final LocationsService locationsService;

    @GetMapping()
    @Operation(summary = "Get all the Survivor Inventories", description = "This endpoint returns a complete list of not infected Survivors.")
    ResponseEntity<List<Location>> getUsersLastLocation() {
        return new ResponseEntity<>(locationsService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping(value = "/{user_id}")
    @Operation(summary = "Get an Specific location from Survivor UUID", description = "This endpoint returns last location by given a Survivor UUID.")
    ResponseEntity<Location> getUserLastLocationById(@PathVariable("user_id") UUID id) {
        return new ResponseEntity<>(locationsService.getUserLocation(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{user_id}")
    @Operation(summary = "Update survivor last location", description = "This endpoint allows to update the user last location on earth.")
    ResponseEntity<Location> updateLastLocation(@PathVariable("user_id") UUID id, @RequestBody UpdateLocationRequest request) {
        return new ResponseEntity<>(locationsService.updateSurvivorLastLocation(id,request), HttpStatus.OK);
    }
}
