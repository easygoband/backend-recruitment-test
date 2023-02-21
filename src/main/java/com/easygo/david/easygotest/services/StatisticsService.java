package com.easygo.david.easygotest.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StatisticsService {

    @Autowired
    private final InventoriesService inventoriesService;


    public Double getInfectedSurvivorsRate(boolean infected) {
        double total = inventoriesService.getAllInventories().size();
        double target = inventoriesService.getAllInventoriesCustom(infected).size();

        System.out.println(total);
        System.out.println(target);
        return (target / total) * 100;
    }

    public Map<String, String> avgAmountOfResources() {
        var target = inventoriesService.getAllInventoriesCustom(false);

        Map<String, Integer> allItems = new HashMap<>();
        target.forEach(survivorInventory -> {
            survivorInventory.getInventory_item().forEach(invItem -> {
                int current = allItems.getOrDefault(invItem.getItem().getName(),0);
                allItems.put(invItem.getItem().getName(), current + invItem.getQuantity());
            });
        });

        Map<String,String> itemsPerSurvivor = new HashMap<>();
        int totalNonInfected = inventoriesService.getAllInventoriesCustom(false).size();
        System.out.println("Total survivors="+totalNonInfected);

        allItems.forEach((k, v) -> {
            System.out.println(k + "->" +  v);
            itemsPerSurvivor.put(k,(v/totalNonInfected)+" per survivor");
        });

        return itemsPerSurvivor;
    }

    public String avgAmountOfResource(String item) {
        return avgAmountOfResources().get(item);
    }


//    3.  Average amount of each kind of resource by survivor (e.g. 5 waters per survivor)
//    4.  Points lost because of infected survivor.
}
