package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.models.SurvivorInventory;
import com.easygo.david.easygotest.services.InventoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/survivors/inventory")
@Tag(name = "Survivor Inventories Controller", description = "This Controller contains the endpoint for getting the information about the items a Survivor have.")
@AllArgsConstructor
public class InventoryController {
    @Autowired
    private final InventoriesService inventoriesService;

    @GetMapping()    //TODO: update to get just the not infected inventories, but just on this controller
    @Operation(summary = "Get all the Survivor Inventories", description = "This endpoint returns a complete list of not infected Survivors.")
    ResponseEntity<List<SurvivorInventory>> getAllInventories() {
        return new ResponseEntity<>(inventoriesService.getAllInventories(), HttpStatus.OK);
    }

    @GetMapping(value = "/{user_id}")
    @Operation(summary = "Get an Inventory from Survivor ID", description = "This endpoint returns the Inventory from a non Infected Survivor.")
    ResponseEntity<SurvivorInventory> getSingleSurvivorInventory(@PathVariable("user_id") String id) {
        return new ResponseEntity<>(inventoriesService.getSingleInventory(UUID.fromString(id)), HttpStatus.OK);
    }
}
