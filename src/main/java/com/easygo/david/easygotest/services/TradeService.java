package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.TradeRequest;
import com.easygo.david.easygotest.models.InventoryItemRecord;
import com.easygo.david.easygotest.models.SurvivorInventory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TradeService {

    @Autowired
    private final InventoriesService inventoriesService;

    @Autowired
    private final InfectedRegisterService infectedRegisterService;

    public List<SurvivorInventory> exchange(UUID own_id, UUID target_id, TradeRequest tradeRequest) {
        var currentSurvivor = inventoriesService.getSingleInventory(own_id);
        var targetSurvivor = inventoriesService.getSingleInventory(target_id);

        var register = infectedRegisterService.findBySurvivorId(own_id);
        if (register.getInfected())
            throw new RuntimeException("Used if already infectedTrade not available.");

        Set<InventoryItemRecord> currentSet = currentSurvivor.getInventory_item();
        Set<InventoryItemRecord> targetSet = targetSurvivor.getInventory_item();

        int pointsFromCurrent = getPointsToTrade(currentSet, tradeRequest.getSendItems());
        int pointsFromTarget = getPointsToTrade(targetSet, tradeRequest.getGetItems());

        if (pointsFromCurrent != pointsFromTarget)
            throw new RuntimeException("Exchange points must be equal!");

        addItemsToSet(currentSet, tradeRequest.getGetItems());
        addItemsToSet(targetSet, tradeRequest.getSendItems());

        currentSurvivor.setInventory_item(currentSet);
        targetSurvivor.setInventory_item(targetSet);

        var ic = inventoriesService.updateInventoryValues(currentSurvivor);
        var it = inventoriesService.updateInventoryValues(targetSurvivor);

        return List.of(ic,it);
    }

    private void addItemsToSet(Set<InventoryItemRecord> set, Map<String, Integer> itemsToAdd) {
        for (var itemRecord : set) {
            String ex_item = itemRecord.getItem().getName();
            Integer sumOfItems = itemRecord.getQuantity() + itemsToAdd.get(ex_item);
            itemRecord.setQuantity(sumOfItems);
        }
    }


    private Integer getPointsToTrade(Set<InventoryItemRecord> set, Map<String, Integer> itemsToTake) {
        int totalPoints = 0;
        for (var itemRecord : set) {
            String ex_item = itemRecord.getItem().getName();

            Integer amountToTake = itemsToTake.get(ex_item);

            if (amountToTake > itemRecord.getQuantity()) {
                throw new RuntimeException("want to take: " + amountToTake + " of " + ex_item + " but just have " + itemRecord.getQuantity());
            } else {
                Integer difference = itemRecord.getQuantity() - amountToTake;
                totalPoints += itemRecord.getItem().getPoints() * amountToTake;
                itemRecord.setQuantity(difference);
            }
        }
        return totalPoints;
    }
}
