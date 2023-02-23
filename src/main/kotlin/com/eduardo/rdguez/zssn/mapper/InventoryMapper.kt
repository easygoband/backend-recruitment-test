package com.eduardo.rdguez.zssn.mapper

import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.domain.Survivor

class InventoryMapper {
  companion object {
    fun toEntity(survivor: Survivor, item: Item, quantity: Int): SurvivorInventory {
      return SurvivorInventory(
        survivor = survivor,
        item = item,
        quantity = quantity
      )
    }
  }
}