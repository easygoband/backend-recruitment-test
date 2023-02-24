package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest
import com.eduardo.rdguez.zssn.service.SurvivorService
import javax.validation.Valid
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

}