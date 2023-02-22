package com.easygo.david.easygotest.util;

import com.easygo.david.easygotest.models.InventoryItemRecord;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalPointsUpdater {

    public Integer updateTotalPointAmount(Set<InventoryItemRecord> currentSet) {
        int totalPoints = 0;

        for (InventoryItemRecord i: currentSet) {
            totalPoints += (i.getQuantity() * i.getItem().getPoints());
        }
        return totalPoints;
    }
}
