package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.models.SurvivorInventory;
import com.easygo.david.easygotest.services.StatisticsService;
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
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/survivors/statistics")
@Tag(name = "Statistics Controller", description = "This Controller contains the endpoint for getting util information about the Statistics of the virus.")
@AllArgsConstructor
public class StatisticsController {

    @Autowired
    private final StatisticsService statisticsService;
    
    @GetMapping("/infected-ratio/{infected}")
    @Operation(summary = "Get the percentage of infected/non-infected survivors", description = "This endpoint returns the percentage of selected infected/non-infected survivor.")
    ResponseEntity<Double> percentageOfInfectedSurvivors(@PathVariable("infected") Boolean infected) {
        return new ResponseEntity<>(statisticsService.getInfectedSurvivorsRate(infected), HttpStatus.OK);
    }



    @GetMapping("/avg-res-amount")
    @Operation(summary = "Average amount of resources.", description = "Average amount of each kind of resource of all survivors.")
    ResponseEntity<Map<String, String>> averageAmountForResources() {
        return new ResponseEntity<>(statisticsService.avgAmountOfResources(), HttpStatus.OK);
    }

    @GetMapping("/avg-res-amount/{item_name}")
    @Operation(summary = "Average amount for specific resource.", description = "Average amount for specific resource of all survivors.")
    ResponseEntity<String> averageAmountForSpecificResource(@PathVariable("item_name") String item) {
        return new ResponseEntity<>(statisticsService.avgAmountOfResource(item.toLowerCase()), HttpStatus.OK);
    }

}
