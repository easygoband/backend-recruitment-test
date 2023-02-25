package com.eduardo.rdguez.zssn.mapper

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.dto.SurvivorInventoryDto

class SurvivorInventoryMapper {
  companion object {
    fun toEntity(survivor: Survivor, item: Item, quantity: Int): SurvivorInventory {
      return SurvivorInventory(
        survivor = survivor,
        item = item,
        quantity = quantity
      )
    }

    fun toDto(survivorInventory: List<SurvivorInventory>): List<SurvivorInventoryDto> {
      return survivorInventory.map { inventory ->
        SurvivorInventoryDto(
          item = inventory.item.name,
          quantity = inventory.quantity
        )
      }
    }
  }
}