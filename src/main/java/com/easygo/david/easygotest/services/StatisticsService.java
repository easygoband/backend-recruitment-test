package com.easygo.david.easygotest.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StatisticsService {

    @Autowired
    private final InventoriesService inventoriesService;


    public Double getInfectedSurvivorsRate(boolean infected) {
        double total = inventoriesService.getAllInventories().size();
        double target = inventoriesService.getAllInventoriesCustom(infected).size();

        return (target / total) * 100;
    }

    public Map<String, String> avgAmountOfResources() {
        var target = inventoriesService.getAllInventoriesCustom(false);

        Map<String, Integer> allItems = new HashMap<>();
        target.forEach(survivorInventory -> survivorInventory.getInventory_item().forEach(invItem -> {
            int current = allItems.getOrDefault(invItem.getItem().getName(), 0);
            allItems.put(invItem.getItem().getName(), current + invItem.getQuantity());
        }));

        Map<String, String> itemsPerSurvivor = new HashMap<>();
        int totalNonInfected = inventoriesService.getAllInventoriesCustom(false).size();

        allItems.forEach((k, v) -> {
            itemsPerSurvivor.put(k, (v / totalNonInfected) + " per survivor");
        });

        return itemsPerSurvivor;
    }

    public String avgAmountOfResource(String item) {
        return avgAmountOfResources().get(item);
    }

    public Map<String, String> resourcesPointsLost() {
        var target = inventoriesService.getAllInventoriesCustom(true);

        Map<String, Integer> allItems = new HashMap<>();
        target.forEach(survivorInventory ->
                survivorInventory.getInventory_item().forEach(invItem -> {
                    int current = allItems.getOrDefault(invItem.getItem().getName(), 0);
                    allItems.put(invItem.getItem().getName(), current + (invItem.getQuantity() * invItem.getItem().getPoints()));
                }));

        Map<String, String> pointLostPerItem = new HashMap<>();

        allItems.forEach((k, v) -> pointLostPerItem.put(k, v  + " total points per infected survivor"));
        
        return pointLostPerItem;
    }

    public Map<String, String> resourcesPointsLost(UUID id) {

        var target = inventoriesService.getSingleInventory(id);

        Map<String, Integer> infSurvivorItems = new HashMap<>();

        target.getInventory_item().forEach(invItem -> {
            int current = infSurvivorItems.getOrDefault(invItem.getItem().getName(), 0);
            infSurvivorItems.put(invItem.getItem().getName(), current + (invItem.getQuantity() * invItem.getItem().getPoints()));
        });

        Map<String, String> pointLostPerItem = new HashMap<>();

        infSurvivorItems.forEach((k, v) -> pointLostPerItem.put(k, v  + " total points per infected survivor"));

        return pointLostPerItem;
    }
}
