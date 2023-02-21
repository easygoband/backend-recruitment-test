package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.SurvivorInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurvivorInventoryRepository extends JpaRepository<SurvivorInventory, UUID> {
}
