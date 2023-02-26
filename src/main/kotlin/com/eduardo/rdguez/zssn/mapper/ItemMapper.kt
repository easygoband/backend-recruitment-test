package com.eduardo.rdguez.zssn.mapper

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.dto.ItemDto

class ItemMapper {
  companion object {
    fun toDto(item: Item): ItemDto {
      return ItemDto(
        id = item.id!!,
        name = item.name,
        points = item.points,
      )
    }
  }
}