package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.exception.BadRequestException
import com.eduardo.rdguez.zssn.model.request.InfectionLogRequest
import com.eduardo.rdguez.zssn.service.InfectionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Infections Controller")
@RequestMapping("/api/v1/infections")
class InfectionsController(
  private val infectionService: InfectionService
) {

  @PostMapping
  @Operation(summary = "Report survivor infection")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Survivor infection reported"),
    ]
  )
  fun reportSurvivorInfection(
    @Valid @RequestBody infectionLogRequest: InfectionLogRequest,
    errors: Errors
  ) {
    if (errors.hasErrors()) {
      throw BadRequestException(errors)
    }
    infectionService.reportSurvivorInfection(infectionLogRequest)
  }

}