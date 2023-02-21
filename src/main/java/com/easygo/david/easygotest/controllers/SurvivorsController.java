package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.controllers.responses.ResponseAction;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.services.InfectedRegisterService;
import com.easygo.david.easygotest.services.InventoriesService;
import com.easygo.david.easygotest.services.LocationsService;
import com.easygo.david.easygotest.services.SurvivorsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
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
    ResponseEntity<List<Survivor>> getAllSurvivors() {
        return new ResponseEntity<>(survivorsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    ResponseEntity<Survivor> getSurvivorById(@PathVariable("user_id") String id) {
        return new ResponseEntity<>(survivorsService.findSurvivorById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    ResponseEntity<ResponseAction> registerNewSurvivor(@RequestBody NewSurvivorRequest requestBody) {
        Survivor survivor = survivorsService.registrateSurvivor(requestBody);
        locationsService.registrateLocation(survivor, requestBody);
        infectedRegisterService.createInfectedRegister(survivor);
        inventoriesService.createInventoryForUser(survivor,requestBody);
        return new ResponseEntity<>(new ResponseAction("created",survivor.getId().toString()), HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    ResponseEntity<Survivor> updateExistingUser(@PathVariable("user_id") String id, @RequestBody UpdateSurvivorRequest request) {
        return new ResponseEntity<>(survivorsService.updateSurvivorData(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{user_id}")
    ResponseEntity<ResponseAction> deleteExistingSurvivor(@PathVariable("user_id") String id) {
        locationsService.deleteSurvivorLocation(id);
        infectedRegisterService.deleteInfectedRegister(id);
        inventoriesService.deleteInventory(id);
        survivorsService.deleteSurvivor(id);
        return new ResponseEntity<>(new ResponseAction("User deleted",id), HttpStatus.OK);
    }
}