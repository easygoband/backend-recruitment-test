package com.easygo.david.easygotest.controllers;


import com.easygo.david.easygotest.models.InfectedRegister;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.services.InfectedRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/survivors/infected")
@AllArgsConstructor
public class InfectedRegisterController {
    @Autowired
    private final InfectedRegisterService infectedRegisterService;

    @GetMapping
    ResponseEntity<List<InfectedRegister>> getAllSurvivors() {
        return new ResponseEntity<>(infectedRegisterService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    ResponseEntity<InfectedRegister> getSurvivorById(@PathVariable("user_id") String id) {
        return new ResponseEntity<>(infectedRegisterService.findBySurvivorId(id), HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    ResponseEntity<InfectedRegister> updateUserInfectedAlert(@PathVariable("user_id") String id) {
        return new ResponseEntity<>(infectedRegisterService.updaterUserInfectedAlerts(id), HttpStatus.OK);
    }
}
