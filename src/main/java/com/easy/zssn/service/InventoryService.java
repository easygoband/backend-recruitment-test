package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Inventory;
import com.easy.zssn.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements InventoryInterface {
    @Autowired
    public InventoryRepository inventoryRepository;
    @Override
    public Inventory upsertInventory(Inventory newInventory) {
        return inventoryRepository.save(newInventory);
    }
    @Override
    public boolean deleteInventory(int id) {
        boolean resultado= false;
        try {
            inventoryRepository.deleteById(id);
            resultado = true;
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }
    @Override
    public Inventory findByIDInventory(int id) {
        return inventoryRepository.findById(id).get();
    }
    @Override
    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findAllBySurvivorID(int id) {
        return inventoryRepository.findAllBySurvivorID(id);
    }
}
