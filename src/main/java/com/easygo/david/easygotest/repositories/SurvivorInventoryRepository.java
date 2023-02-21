package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.SurvivorInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SurvivorInventoryRepository extends JpaRepository<SurvivorInventory, UUID> {
}
