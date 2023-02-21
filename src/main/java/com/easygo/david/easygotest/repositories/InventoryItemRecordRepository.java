package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.InventoryItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRecordRepository extends JpaRepository<InventoryItemRecord, Long> {
}
