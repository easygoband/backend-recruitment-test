package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest
import com.eduardo.rdguez.zssn.service.SurvivorService
import javax.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/survivors")
class SurvivorsController(
  private val survivorService: SurvivorService
) {

  @PostMapping
  fun saveSurvivor(@Valid @RequestBody survivorRequest: SurvivorRequest): SurvivorDto {
    return survivorService.saveSurvivor(survivorRequest)
  }

  @PatchMapping("/{id}/location")
  fun updateLocation(
    @PathVariable(name = "id") id: Long,
    @Valid @RequestBody locationRequest: LocationRequest
  ): SurvivorDto {
    return survivorService.updateLocation(id, locationRequest)
  }

}