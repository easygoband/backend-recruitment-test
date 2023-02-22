package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.controllers.responses.ResponseAction;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.services.InfectedRegisterService;
import com.easygo.david.easygotest.services.InventoriesService;
import com.easygo.david.easygotest.services.LocationsService;
import com.easygo.david.easygotest.services.SurvivorsService;
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
@Tag(name = "Survivors Controller", description = "This Controller contains the endpoint for getting information about survivor users registered on the database.")
@RequestMapping("/api/v1/survivors")
@AllArgsConstructor
public class SurvivorsController {
    @Autowired
    private final SurvivorsService survivorsService;
    @Autowired
    private final LocationsService locationsService;
    @Autowired
    private final InventoriesService inventoriesService;
    @Autowired
    private final InfectedRegisterService infectedRegisterService;

    @GetMapping
    @Operation(summary = "Get all registered Survivors", description = "This endpoint returns a complete list of al survivors.")
    ResponseEntity<List<Survivor>> getAllSurvivors() {
        return new ResponseEntity<>(survivorsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    @Operation(summary = "Get registered Survivor by ID", description = "This endpoint returns a Survivor by giving his UUID identifier.")
    ResponseEntity<Survivor> getSurvivorById(@PathVariable("user_id") UUID id) {
        return new ResponseEntity<>(survivorsService.findSurvivorById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Registrate a new Survivor", description = "This endpoint creates a new Survivor with all the requirements for being part of the system.")
    ResponseEntity<ResponseAction> registerNewSurvivor(@RequestBody NewSurvivorRequest requestBody) {
        Survivor survivor = survivorsService.registrateSurvivor(requestBody);
        locationsService.registrateLocation(survivor, requestBody);
        infectedRegisterService.createInfectedRegister(survivor);
        inventoriesService.createInventoryForUser(survivor,requestBody);
        return new ResponseEntity<>(new ResponseAction("created",survivor.getId().toString()), HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    @Operation(summary = "Update an existing Survivor", description = "This endpoint returns an updated Survivor by receiving some field updates.")
    ResponseEntity<Survivor> updateExistingUser(@PathVariable("user_id") UUID id, @RequestBody UpdateSurvivorRequest request) {
        return new ResponseEntity<>(survivorsService.updateSurvivorData(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{user_id}")
    @Operation(summary = "Delete an existing Survivor", description = "This endpoint deletes a Survivor with all their content in the system.")
    ResponseEntity<ResponseAction> deleteExistingSurvivor(@PathVariable("user_id") UUID id) {
        inventoriesService.deleteInventory(id);
        locationsService.deleteSurvivorLocation(id);
        infectedRegisterService.deleteInfectedRegister(id);
        survivorsService.deleteSurvivor(id);
        return new ResponseEntity<>(new ResponseAction("User deleted",id.toString()), HttpStatus.OK);
    }
}