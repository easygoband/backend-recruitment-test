package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.TradeRequest;
import com.easygo.david.easygotest.models.InventoryItemRecord;
import com.easygo.david.easygotest.models.SurvivorInventory;
import com.easygo.david.easygotest.util.TotalPointsUpdater;
import com.easygo.david.easygotest.util.TradeHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new RuntimeException("Used if already infected, Trade not available.");

        Set<InventoryItemRecord> currentSet = currentSurvivor.getInventory_item();
        Set<InventoryItemRecord> targetSet = targetSurvivor.getInventory_item();
        
        int pointsFromCurrent = tradeHandler.getPointsToTrade(currentSet, tradeRequest.getSendItems());
        int pointsFromTarget = tradeHandler.getPointsToTrade(targetSet, tradeRequest.getGetItems());

        if (pointsFromCurrent != pointsFromTarget)
            throw new RuntimeException("Exchange points must be equal!");

        tradeHandler.addItemsToSet(currentSet, tradeRequest.getGetItems());
        tradeHandler.addItemsToSet(targetSet, tradeRequest.getSendItems());


        currentSurvivor.setInventory_item(currentSet);

        currentSurvivor.setTotal(updater.updateTotalPointAmount(currentSet));
        targetSurvivor.setInventory_item(targetSet);
        targetSurvivor.setTotal(updater.updateTotalPointAmount(targetSet));

        var ic = inventoriesService.updateInventoryValues(currentSurvivor);
        var it = inventoriesService.updateInventoryValues(targetSurvivor);

        return List.of(ic, it);
    }

    TotalPointsUpdater updater;
    TradeHandler tradeHandler;
}



