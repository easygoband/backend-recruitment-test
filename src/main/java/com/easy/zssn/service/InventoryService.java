package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Inventory;
import com.easy.zssn.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements InventoryInterface {
    @Autowired
    public InventoryRepository InventoryService;
    @Override
    public Inventory upsertInventory(Inventory newInventory) {
        return InventoryService.save(newInventory);
    }
    @Override
    public boolean deleteInventory(int id) {
        boolean resultado= false;
        try {
            InventoryService.deleteById(id);
            resultado = true;
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }
    @Override
    public Inventory findByIDInventory(int id) {
        return InventoryService.findById(id).get();
    }
    @Override
    public List<Inventory> findAllInventory() {
        return InventoryService.findAll();
    }
}
