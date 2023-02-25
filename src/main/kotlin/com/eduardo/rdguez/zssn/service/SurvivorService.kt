package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest

interface SurvivorService {

  fun findAllInfectedSurvivors(): List<Survivor>
  fun findSurvivorById(id: Long): Survivor
  fun saveSurvivor(survivorRequest: SurvivorRequest): SurvivorDto
  fun saveSurvivor(survivor: Survivor): Survivor
  fun updateSurvivorLocation(id: Long, locationRequest: LocationRequest): SurvivorDto
  fun updateSurvivorToInfected(id: Long): SurvivorDto
  fun countAllInfectedSurvivors(): Long
  fun countAllUninfectedSurvivors(): Long
  fun countAllSurvivors(): Long

}