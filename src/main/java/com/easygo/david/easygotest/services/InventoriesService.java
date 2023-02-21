package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.models.*;
import com.easygo.david.easygotest.repositories.InventoryItemRecordRepository;
import com.easygo.david.easygotest.repositories.ItemsRepository;
import com.easygo.david.easygotest.repositories.SurvivorInventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
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
    private final ItemsRepository itemsRepository;


    public List<SurvivorInventory> getAllInventories() {
        return survivorInventoryRepository.findAll();
    }

    public SurvivorInventory getSingleInventory(UUID uuid) {
        if (uuid == null) throw new IllegalStateException("id can't be null");

        var found = survivorInventoryRepository.findById(uuid);

        if (found.isPresent())
            return found.get();
        else
            throw new IllegalStateException("id not found");
    }

    public void createInventoryForUser(Survivor survivor, NewSurvivorRequest requestBody) {
        if (survivor == null) throw new IllegalStateException("Survivor can't be null");
        SurvivorInventory survivorInventory = new SurvivorInventory(survivor);
        survivorInventory.setSurvivor_id(survivor.getId());
        survivorInventoryRepository.save(survivorInventory);

        Integer[] totalPoints = {0};

        var fields = requestBody.getClass().getDeclaredFields();
        for (Field field : fields) {
            switch (field.getName()) {
                case "water" -> saveItemOnInventoryRecord(totalPoints,field.getName(), requestBody::getWater, survivorInventory);
                case "food" -> saveItemOnInventoryRecord(totalPoints,field.getName(), requestBody::getFood, survivorInventory);
                case "medication" ->
                        saveItemOnInventoryRecord(totalPoints,field.getName(), requestBody::getMedication, survivorInventory);
                case "ammunition" ->
                        saveItemOnInventoryRecord(totalPoints,field.getName(), requestBody::getAmmunition, survivorInventory);
            }
        }
        survivorInventory.setTotal(totalPoints[0]);
        survivorInventoryRepository.save(survivorInventory);
    }

    private void saveItemOnInventoryRecord(Integer[] totalPoints,String name, Supplier<Integer> param, SurvivorInventory inventory) {
        Item item = itemsService.findByName(name).get(0);
        int quantity = param.get();
        totalPoints[0] += (quantity * item.getPoints());
        InventoryItemRecord record = new InventoryItemRecord(quantity, item, inventory);
        inventoryItemRecordRepository.save(record);
    }


    public void deleteInventory(String id) {
    }
}

