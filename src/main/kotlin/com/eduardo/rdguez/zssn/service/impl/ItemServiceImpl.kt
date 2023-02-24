package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.repository.ItemRepository
import com.eduardo.rdguez.zssn.service.ItemService
import java.util.*
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(
  private val itemRepository: ItemRepository
) : ItemService {

  override fun findItemByName(name: String): Optional<Item> {
    return itemRepository.findByNameIgnoreCase(name)
  }

}