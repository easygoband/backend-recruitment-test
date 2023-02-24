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
import com.eduardo.rdguez.zssn.util.DecimalUtil
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
  private val survivorService: SurvivorService,
  private val survivorInventoryService: SurvivorInventoryService
) : ReportService {
  private val logger = KotlinLogging.logger {}

  override fun findInfectedSurvivorsPercentage(): InfectionsResponse {
    logger.info { "Find infected survivors percentage" }

    val uninfected: Long = survivorService.countAllUninfectedSurvivors()
    val infected: Long = survivorService.countAllInfectedSurvivors()
    val percentage: Double = ArithmeticUtil.percentage(
      infected.toDouble(),
      uninfected.toDouble(),
    )

    return InfectionsResponse(
      survivors = uninfected,
      infections = infected,
      percentage = DecimalUtil.truncate(percentage)
    )
  }

  override fun findUninfectedSurvivorsPercentage(): NoInfectionsResponse {
    logger.info { "Find uninfected survivors percentage" }

    val survivors: Long = survivorService.countAllSurvivors()
    val uninfected: Long = survivorService.countAllUninfectedSurvivors()
    val percentage: Double = ArithmeticUtil.percentage(
      uninfected.toDouble(),
      survivors.toDouble(),
    )

    return NoInfectionsResponse(
      survivors = survivors,
      noInfections = uninfected,
      percentage = DecimalUtil.truncate(percentage)
    )
  }

  override fun findLostPointsByInfectedSurvivor(): List<LostPointsResponse> {
    logger.info { "Find lost points by infected survivor" }

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
    logger.info { "Find average items by survivor" }

    val uninfected: Long = survivorService.countAllUninfectedSurvivors()
    val survivorInventory: List<SurvivorInventory> = survivorInventoryService.findAllSurvivorInventory()

    return survivorInventory.groupBy { it.item }
      .map { group ->
        val totalQuantity: Int = group.value.sumOf { it.quantity }
        val average: Double = ArithmeticUtil.average(totalQuantity.toDouble(), uninfected.toDouble())

        ItemAverage(
          name = group.key.name,
          average = average.toInt()
        )
      }
  }

}