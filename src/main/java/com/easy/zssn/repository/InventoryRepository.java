package com.easy.zssn.repository;

import java.util.List;

import com.easy.zssn.model.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    List<Inventory> findAllBySurvivorID(Integer survivorID);
}
