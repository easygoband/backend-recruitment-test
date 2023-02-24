package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.model.request.InfectionLogRequest
import com.eduardo.rdguez.zssn.service.InfectionService
import javax.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/infections")
class InfectionsController(
  private val infectionService: InfectionService
) {

  @PostMapping
  fun reportSurvivorInfection(@Valid @RequestBody infectionLogRequest: InfectionLogRequest) {
    infectionService.reportSurvivorInfection(infectionLogRequest)
  }

}