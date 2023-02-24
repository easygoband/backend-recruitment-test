package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.mapper.SurvivorMapper
import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.ItemAverage
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse
import com.eduardo.rdguez.zssn.service.ReportService
import com.eduardo.rdguez.zssn.service.SurvivorInventoryService
import com.eduardo.rdguez.zssn.service.SurvivorService
import com.eduardo.rdguez.zssn.util.ArithmeticUtil
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
  private val survivorService: SurvivorService,
  private val survivorInventoryService: SurvivorInventoryService
) : ReportService {

  override fun findInfections(): InfectionsResponse {
    val uninfected: Int = survivorService.countAllUninfectedSurvivors()
    val infected: Int = survivorService.countAllInfectedSurvivors()
    val percentage: String = ArithmeticUtil.percentage(
      infected.toDouble(),
      uninfected.toDouble(),
    )

    return InfectionsResponse(
      survivors = uninfected,
      infections = infected,
      percentage = percentage
    )
  }

  override fun findNoInfections(): NoInfectionsResponse {
    val uninfected: Int = survivorService.countAllUninfectedSurvivors()
    val percentage: String = ArithmeticUtil.percentage(
      uninfected.toDouble(),
      uninfected.toDouble(),
    )

    return NoInfectionsResponse(
      noInfections = uninfected,
      percentage = percentage
    )
  }

  override fun findLostPoints(): List<LostPointsResponse> {
    val infectedList: List<Survivor> = survivorService.findAllInfectedSurvivors()
    return infectedList.map { infected ->
      LostPointsResponse(
        infected = SurvivorMapper.toDto(infected),
        lostPoints = sumInventoryPoints(infected.survivorInventory)
      )
    }
  }

  fun sumInventoryPoints(survivorInventory: List<SurvivorInventory>): Int {
    return survivorInventory.sumOf { it.quantity * it.item.points }
  }

  override fun findAverageItemsBySurvivor(): List<ItemAverage> {
    val uninfected: Int = survivorService.countAllUninfectedSurvivors()
    val survivorInventory: List<SurvivorInventory> = survivorInventoryService.findAllInventory()

    return survivorInventory.groupBy { it.item }
      .map { group ->
        val totalQuantity: Int = group.value.sumOf { it.quantity }
        val average: String = ArithmeticUtil.average(totalQuantity.toDouble(), uninfected)
        ItemAverage(
          name = group.key.name,
          average = average
        )
      }
  }

}