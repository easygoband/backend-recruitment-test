package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.exception.BadRequestException
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest
import com.eduardo.rdguez.zssn.service.SurvivorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Survivors Controller")
@RequestMapping("/api/v1/survivors")
class SurvivorsController(
  private val survivorService: SurvivorService
) {

  @PostMapping
  @Operation(summary = "Save a survivor")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "201", description = "Survivor created"),
    ]
  )
  fun saveSurvivor(
    @Valid @RequestBody survivorRequest: SurvivorRequest,
    errors: Errors
  ): ResponseEntity<SurvivorDto> {
    val survivor = survivorService.saveSurvivor(survivorRequest)

    if (errors.hasErrors()) {
      throw BadRequestException("$errors")
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(survivor)
  }

  @PatchMapping("/{id}/location")
  @Operation(summary = "Update a survivor's location")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Survivor's location updated"),
      ApiResponse(responseCode = "404", description = "Survivor not found"),
    ]
  )

  fun updateLocation(
    @PathVariable(name = "id") id: Long,
    @Valid @RequestBody locationRequest: LocationRequest,
    errors: Errors
  ): ResponseEntity<SurvivorDto> {
    val survivor = survivorService.updateSurvivorLocation(id, locationRequest)

    if (errors.hasErrors()) {
      throw BadRequestException("$errors")
    }
    return ResponseEntity.status(HttpStatus.OK).body(survivor)
  }

}