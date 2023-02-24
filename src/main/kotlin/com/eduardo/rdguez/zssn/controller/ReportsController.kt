package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.ItemAverage
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse
import com.eduardo.rdguez.zssn.service.ReportService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Reports Controller")
@RequestMapping("/api/v1/reports")
class ReportsController(
  private val reportService: ReportService,
) {

  @GetMapping("/infections")
  @Operation(summary = "Get infected of survivors percentage")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Infected of survivors percentage found"),
    ]
  )
  fun findInfections(): InfectionsResponse {
    return reportService.findInfectedSurvivorsPercentage()
  }

  @GetMapping("/no-infections")
  @Operation(summary = "Get uninfected survivors percentage")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Uninfected of survivors percentage found"),
    ]
  )
  fun findNotInfections(): NoInfectionsResponse {
    return reportService.findUninfectedSurvivorsPercentage()
  }

  @GetMapping("/lost-points")
  @Operation(summary = "Get lost points by infected survivor")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Lost points by infected survivor found"),
    ]
  )
  fun findLostPoints(): List<LostPointsResponse> {
    return reportService.findLostPointsByInfectedSurvivor()
  }

  @GetMapping("/average-items")
  @Operation(summary = "Get average items by survivor")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Average items by survivor found"),
    ]
  )
  fun findItemsAverage(): List<ItemAverage> {
    return reportService.findAverageItemsBySurvivor()
  }

}