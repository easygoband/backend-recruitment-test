package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.exception.BadRequestException
import com.eduardo.rdguez.zssn.mapper.SurvivorInventoryMapper
import com.eduardo.rdguez.zssn.model.request.ExchangeRequest
import com.eduardo.rdguez.zssn.model.request.ItemsRequest
import com.eduardo.rdguez.zssn.model.response.ExchangeResponse
import com.eduardo.rdguez.zssn.service.ExchangeService
import com.eduardo.rdguez.zssn.service.SurvivorService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ExchangeServiceImpl(
  private val survivorService: SurvivorService,
) : ExchangeService {
  private val logger = KotlinLogging.logger {}

  @Transactional(propagation = Propagation.REQUIRED)
  override fun itemsExchange(exchangeRequest: ExchangeRequest): ExchangeResponse {
    logger.info { "Items exchange initiated" }

    val sender: Survivor = survivorService.findSurvivorById(exchangeRequest.senderId)
    val receiver: Survivor = survivorService.findSurvivorById(exchangeRequest.receiverId)

    if (validateInventoryIsNotEmpty(sender, receiver))
      throw BadRequestException("Not enough items")

    if (validateExchangePoints(sender, receiver, exchangeRequest))
      throw BadRequestException("Exchange points must be equal")

    val senderInventory: List<SurvivorInventory> = updateInventoryQuantities(
      sender.survivorInventory, exchangeRequest.senderItems, false
    )
    val receiverInventory: List<SurvivorInventory> = updateInventoryQuantities(
      receiver.survivorInventory, exchangeRequest.receiverItems, true
    )

    updateSurvivorInventory(sender, receiver)

    return ExchangeResponse(
      senderInventory = SurvivorInventoryMapper.toDto(senderInventory),
      receiverInventory = SurvivorInventoryMapper.toDto(receiverInventory)
    )
  }

  fun getInventoryPoints(survivor: Survivor, itemsRequest: ItemsRequest): Int {
    return survivor.survivorInventory.sumOf { inventory ->
      val quantity = itemsRequest[inventory.item.name]

      if (inventory.quantity < quantity) {
        throw BadRequestException(
          "Survivor with ID: ${survivor.id} doesn't have enough items: ${inventory.item.name} to exchange"
        )
      }

      val points = getItemPointsByName(survivor.survivorInventory, inventory.item.name)
      points * quantity
    }
  }

  fun getItemPointsByName(survivorInventoryList: List<SurvivorInventory>, name: String): Int {
    val survivorInventory: SurvivorInventory = survivorInventoryList.find { inventory ->
      inventory.item.name.lowercase() == name.lowercase()
    }!!
    return survivorInventory.item.points
  }

  fun updateInventoryQuantities(
    survivorInventory: List<SurvivorInventory>,
    itemsRequest: ItemsRequest,
    add: Boolean
  ): List<SurvivorInventory> {
    return survivorInventory.map { inventory ->
      val quantity = itemsRequest[inventory.item.name]

      if (add) inventory.quantity += quantity
      else inventory.quantity -= quantity

      inventory
    }
  }

  fun updateSurvivorInventory(sender: Survivor, receiver: Survivor) {
    logger.info { "Update survivor inventory" }

    survivorService.saveSurvivor(sender)
    survivorService.saveSurvivor(receiver)
  }

  fun validateExchangePoints(
    sender: Survivor,
    receiver: Survivor,
    exchangeRequest: ExchangeRequest
  ): Boolean {
    val senderPoints: Int = getInventoryPoints(sender, exchangeRequest.senderItems)
    val receiverPoints: Int = getInventoryPoints(receiver, exchangeRequest.receiverItems)
    return senderPoints != receiverPoints
  }

  fun validateInventoryIsNotEmpty(
    sender: Survivor,
    receiver: Survivor,
  ): Boolean {
    return sender.survivorInventory.isEmpty() || receiver.survivorInventory.isEmpty()
  }

}