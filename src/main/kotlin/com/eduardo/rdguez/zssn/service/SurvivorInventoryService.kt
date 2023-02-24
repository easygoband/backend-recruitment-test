package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.model.request.ItemsRequest

interface SurvivorInventoryService {

  fun findInventoryBySurvivor(survivor: Survivor): List<SurvivorInventory>
  fun findAllSurvivorInventory(): List<SurvivorInventory>
  fun saveInventory(survivorInventory: SurvivorInventory): SurvivorInventory
  fun assignInventory(survivor: Survivor, itemsRequest: ItemsRequest): List<SurvivorInventory>

}