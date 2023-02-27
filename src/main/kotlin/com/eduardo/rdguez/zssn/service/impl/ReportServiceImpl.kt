package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.mapper.SurvivorMapper
import com.eduardo.rdguez.zssn.model.response.AverageItemResponse
import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse
import com.eduardo.rdguez.zssn.service.ReportService
import com.eduardo.rdguez.zssn.service.SurvivorInventoryService
import com.eduardo.rdguez.zssn.service.SurvivorService
import com.eduardo.rdguez.zssn.util.ArithmeticUtil
import com.eduardo.rdguez.zssn.util.DecimalUtil
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReportServiceImpl(
  private var survivorInventoryService: SurvivorInventoryService,
  private var survivorService: SurvivorService
) : ReportService {
  private val logger = KotlinLogging.logger {}

  @Transactional(readOnly = true)
  override fun findInfectedSurvivorsPercentage(): InfectionsResponse {
    logger.info { "Find infected survivors percentage" }

    val survivors: Long = survivorService.countAllSurvivors()
    val infected: Long = survivorService.countAllInfectedSurvivors()
    val percentage: Double = ArithmeticUtil.percentage(
      infected.toDouble(),
      survivors.toDouble(),
    )

    return InfectionsResponse(
      survivors = survivors,
      infections = infected,
      percentage = DecimalUtil.truncate(percentage)
    )
  }

  @Transactional(readOnly = true)
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

  @Transactional(readOnly = true)
  override fun findLostPointsByInfectedSurvivor(): List<LostPointsResponse> {
    logger.info { "Find lost points by infected survivor" }

    val infectedList: List<Survivor> = survivorService.findAllInfectedSurvivors()
    return infectedList.map { infected ->
      LostPointsResponse(
        infected = SurvivorMapper.toDto(infected),
        lostPoints = getInventoryPoints(infected.survivorInventory)
      )
    }
  }

  @Transactional(readOnly = true)
  override fun findAverageItemsBySurvivor(): List<AverageItemResponse> {
    logger.info { "Find average items by survivor" }

    val survivorInventory: List<SurvivorInventory> = survivorInventoryService.findAllSurvivorInventory()
    val uninfected: Long = survivorService.countAllUninfectedSurvivors()

    return survivorInventory.groupBy { it.item.name }
      .map { group ->
        val totalQuantity: Int = group.value.sumOf { it.quantity }
        val average: Double = ArithmeticUtil.average(totalQuantity.toDouble(), uninfected.toDouble())

        AverageItemResponse(
          name = group.key,
          average = average.toInt()
        )
      }
  }

  fun getInventoryPoints(survivorInventory: List<SurvivorInventory>): Int {
    return survivorInventory.sumOf { it.quantity * it.item.points }
  }

}