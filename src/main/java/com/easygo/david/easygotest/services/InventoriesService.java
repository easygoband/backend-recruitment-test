package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.models.InventoryItemRecord;
import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.models.SurvivorInventory;
import com.easygo.david.easygotest.repositories.InventoryItemRecordRepository;
import com.easygo.david.easygotest.repositories.ItemsRepository;
import com.easygo.david.easygotest.repositories.SurvivorInventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class InventoriesService {

    @Autowired
    private final ItemsService itemsService;

    @Autowired
    private final SurvivorInventoryRepository survivorInventoryRepository;
    @Autowired
    private final InventoryItemRecordRepository inventoryItemRecordRepository;

    public void deleteInventory(String id) {
    }

    public void createInventoryForUser(Survivor survivor, NewSurvivorRequest requestBody) {
        if (survivor == null) throw new IllegalStateException("Survivor can't be null");
        SurvivorInventory survivorInventory = new SurvivorInventory(survivor);
        survivorInventory.setSurvivor_id(survivor.getId());
        survivorInventoryRepository.save(survivorInventory);

        var fields = requestBody.getClass().getDeclaredFields();
        for (Field field : fields) {
            switch (field.getName()) {
                case "water" -> saveItemOnInventoryRecord(field.getName(), requestBody::getWater, survivorInventory);
                case "food" ->  saveItemOnInventoryRecord(field.getName(), requestBody::getFood, survivorInventory);
                case "medication" -> saveItemOnInventoryRecord(field.getName(), requestBody::getMedication, survivorInventory);
                case "ammunition" -> saveItemOnInventoryRecord(field.getName(), requestBody::getAmmunition, survivorInventory);
            }
        }
    }

    private void saveItemOnInventoryRecord(String name, Supplier<Integer> param, SurvivorInventory inventory) {
        Item item = itemsService.findByName(name).get(0);
        int quantity = param.get();
        InventoryItemRecord record = new InventoryItemRecord(quantity, item, inventory);
        inventoryItemRecordRepository.save(record);
    }
}

