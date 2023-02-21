package com.easygo.david.easygotest.controllers;


import com.easygo.david.easygotest.models.InfectedRegister;
import com.easygo.david.easygotest.services.InfectedRegisterService;
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
@RequestMapping("/api/v1/survivors/infected")
@Tag(name = "Infected Survivors Controller", description = "This Controller contains the endpoint for getting the register of infection between survivors.")
@AllArgsConstructor
public class InfectedRegisterController {
    @Autowired
    private final InfectedRegisterService infectedRegisterService;

    @GetMapping
    @Operation(summary = "Get all the register of Survivors", description = "This endpoint returns a complete list of all infection register.")
    ResponseEntity<List<InfectedRegister>> getAllSurvivors() {
        return new ResponseEntity<>(infectedRegisterService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    @Operation(summary = "Get the register of a Survivor by ID", description = "This endpoint returns a single register for a Survivor.")
    ResponseEntity<InfectedRegister> getSurvivorById(@PathVariable("user_id") UUID id) {
        return new ResponseEntity<>(infectedRegisterService.findBySurvivorId(id), HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    @Operation(summary = "Update infection alerts", description = "This endpoint update the infection alerts and returns the reported Survivor.")
    ResponseEntity<InfectedRegister> updateUserInfectedAlert(@PathVariable("user_id") UUID id) {
        return new ResponseEntity<>(infectedRegisterService.updaterUserInfectedAlerts(id), HttpStatus.OK);
    }
}
