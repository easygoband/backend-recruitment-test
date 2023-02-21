package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.InventoryItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryItemRecordRepository extends JpaRepository<InventoryItemRecord, Long> {
//    @Modifying
//    @Query("DELETE FROM inventory_item WHERE inventory_item.survivor_id = :_id")
//    void deleteAllBySurvivorId(@Param("_id") UUID id);
}
