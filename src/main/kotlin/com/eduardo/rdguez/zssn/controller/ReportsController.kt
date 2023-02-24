package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
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
    return reportService.findInfections()
  }

  @GetMapping("/no-infections")
  fun findNotInfections(): NoInfectionsResponse {
    return reportService.findNoInfections()
  }

}