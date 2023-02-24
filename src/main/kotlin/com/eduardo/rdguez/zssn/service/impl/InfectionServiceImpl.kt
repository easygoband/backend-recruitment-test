package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.constant.ApiConstants
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.model.request.InfectionLogRequest
import com.eduardo.rdguez.zssn.service.InfectionLogService
import com.eduardo.rdguez.zssn.service.InfectionService
import com.eduardo.rdguez.zssn.service.SurvivorService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class InfectionServiceImpl(
  private val infectionLogService: InfectionLogService,
  private val survivorService: SurvivorService
) : InfectionService {

  @Transactional(propagation = Propagation.REQUIRED)
  override fun reportSurvivorInfection(infectionLogRequest: InfectionLogRequest) {
    val speaker = survivorService.findSurvivorById(infectionLogRequest.speakerId)
    val infected = survivorService.findSurvivorById(infectionLogRequest.infectedId)
    infectionLogService.saveInfectionLog(speaker, infected)

    if (survivorIsInfected(infected)) {
      survivorService.updateSurvivorToInfected(infectionLogRequest.infectedId)
    }
  }

  @Transactional(readOnly = true)
  fun survivorIsInfected(survivor: Survivor): Boolean {
    val reports: Long = infectionLogService.countLogsBySurvivor(survivor)
    return reports >= ApiConstants.REPORTING_LIMIT
  }

}