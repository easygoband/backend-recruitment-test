package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.mapper.SurvivorInventoryMapper
import com.eduardo.rdguez.zssn.model.request.ItemsRequest
import com.eduardo.rdguez.zssn.repository.SurvivorInventoryRepository
import com.eduardo.rdguez.zssn.service.ItemService
import com.eduardo.rdguez.zssn.service.SurvivorInventoryService
import java.util.*
import kotlin.reflect.full.memberProperties
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class SurvivorInventoryServiceImpl(
  private val survivorInventoryRepository: SurvivorInventoryRepository,
  private val itemService: ItemService
) : SurvivorInventoryService {
  private val logger = KotlinLogging.logger {}

  @Transactional(readOnly = true)
  override fun findAllSurvivorInventory(): List<SurvivorInventory> {
    logger.info { "Find all survivor inventory" }

    return survivorInventoryRepository.findAllBySurvivorIsInfectedFalse()
  }

  @Transactional(propagation = Propagation.NESTED)
  override fun saveInventory(survivorInventory: SurvivorInventory): SurvivorInventory {
    logger.info { "Save survivor inventory with ID: ${survivorInventory.survivor.id}" }

    return survivorInventoryRepository.save(survivorInventory)
  }

  @Transactional(propagation = Propagation.REQUIRED)
  override fun assignInventory(survivor: Survivor, itemsRequest: ItemsRequest): List<SurvivorInventory> {
    logger.info { "Assign survivor inventory: $itemsRequest with ID: ${survivor.id}" }

    val survivorInventoryList = mutableListOf<SurvivorInventory>()
    for (prop in ItemsRequest::class.memberProperties) {
      val item: Optional<Item> = itemService.findItemByName(prop.name)
      val quality = prop.get(itemsRequest) as Int

      item.let {
        val survivorInventory: SurvivorInventory = SurvivorInventoryMapper.toEntity(survivor, item.get(), quality)
        survivorInventoryList.add(saveInventory(survivorInventory))
      }
    }

    return survivorInventoryList
  }

}