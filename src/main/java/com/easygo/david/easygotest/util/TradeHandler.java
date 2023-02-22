package com.easygo.david.easygotest.util;

import com.easygo.david.easygotest.models.InventoryItemRecord;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class TradeHandler {
    public void addItemsToSet(Set<InventoryItemRecord> set, Map<String, Integer> itemsToAdd) {
        for (var itemRecord : set) {
            String ex_item = itemRecord.getItem().getName();
            Integer sumOfItems = itemRecord.getQuantity() + itemsToAdd.get(ex_item);
            itemRecord.setQuantity(sumOfItems);
        }
    }


    public Integer getPointsToTrade(Set<InventoryItemRecord> set, Map<String, Integer> itemsToTake) {
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
