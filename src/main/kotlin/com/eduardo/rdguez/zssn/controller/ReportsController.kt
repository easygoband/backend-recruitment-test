package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.ItemAverage
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse
import com.eduardo.rdguez.zssn.service.ReportService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reports")
class ReportsController(
  private val reportService: ReportService,
) {

  @GetMapping("/infections")
  fun findInfections(): InfectionsResponse {
    return reportService.findInfectedSurvivorsPercentage()
  }

  @GetMapping("/no-infections")
  fun findNotInfections(): NoInfectionsResponse {
    return reportService.findUninfectedSurvivorsPercentage()
  }

  @GetMapping("/lost-points")
  fun findLostPoints(): List<LostPointsResponse> {
    return reportService.findLostPointsByInfectedSurvivor()
  }

  @GetMapping("/items-average")
  fun findItemsAverage(): List<ItemAverage> {
    return reportService.findAverageItemsBySurvivor()
  }

}