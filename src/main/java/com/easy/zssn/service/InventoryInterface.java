package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Inventory;

public interface InventoryInterface {
    public Inventory upsertInventory(Inventory newInventory);

    public boolean deleteInventory(int id);

    public Inventory findByIDInventory(int id);

    public List<Inventory> findAllInventory();

    public List<Inventory> findAllBySurvivorID(int id);
}
