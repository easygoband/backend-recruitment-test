package com.easygo.david.easygotest.util;

import com.easygo.david.easygotest.models.InventoryItemRecord;

import java.util.Set;

public class TotalPointsUpdater {

    public Integer updateTotalPointAmount(Set<InventoryItemRecord> currentSet) {
        int totalPoints = 0;

        for (InventoryItemRecord i: currentSet) {
            totalPoints += (i.getQuantity() * i.getItem().getPoints());
        }
        return totalPoints;
    }
}
