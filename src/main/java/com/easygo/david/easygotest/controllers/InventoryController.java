package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.models.SurvivorInventory;
import com.easygo.david.easygotest.services.InventoriesService;
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
@AllArgsConstructor
public class InventoryController {
    @Autowired
    private final InventoriesService inventoriesService;

    @GetMapping()
    ResponseEntity<List<SurvivorInventory>> getAllInventories() {
        return new ResponseEntity<>(inventoriesService.getAllInventories(), HttpStatus.OK);
    }

    @GetMapping(value = "/{user_id}")
    ResponseEntity<SurvivorInventory> getSingleSurvivorInventory(@PathVariable("user_id") String id) {
        return new ResponseEntity<>(inventoriesService.getSingleInventory(UUID.fromString(id)), HttpStatus.OK);
    }
}
