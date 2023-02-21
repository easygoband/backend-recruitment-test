package com.easygo.david.easygotest.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsService {

    @Autowired
    private final InventoriesService inventoriesService;


    public Double getInfectedSurvivorsRate(boolean infected) {
        double total =  inventoriesService.getAllInventories().size();
        double target =  inventoriesService.getAllInventoriesCustom(infected).size();

        System.out.println(total);
        System.out.println(target);
        return (target/total)*100;
    }
}
