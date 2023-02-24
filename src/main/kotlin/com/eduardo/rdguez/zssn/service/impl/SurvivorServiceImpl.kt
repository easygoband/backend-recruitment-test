package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.exception.EntityNotFoundException
import com.eduardo.rdguez.zssn.mapper.SurvivorMapper
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest
import com.eduardo.rdguez.zssn.repository.SurvivorRepository
import com.eduardo.rdguez.zssn.service.LocationService
import com.eduardo.rdguez.zssn.service.SurvivorInventoryService
import com.eduardo.rdguez.zssn.service.SurvivorService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class SurvivorServiceImpl(
  private val survivorRepository: SurvivorRepository,
  private val survivorInventoryService: SurvivorInventoryService,
  private val locationService: LocationService
) : SurvivorService {

  @Transactional(propagation = Propagation.REQUIRED)
  override fun saveSurvivor(survivorRequest: SurvivorRequest): SurvivorDto {
    val lastLocation: Location = locationService.saveLocation(survivorRequest.lastLocation)
    val survivor: Survivor = SurvivorMapper.toEntity(survivorRequest, lastLocation)
    val survivorCreated: Survivor = survivorRepository.save(survivor)
    survivorInventoryService.assignInventory(survivorCreated, survivorRequest.items)
    return SurvivorMapper.toDetailedDto(survivorCreated)
  }

}