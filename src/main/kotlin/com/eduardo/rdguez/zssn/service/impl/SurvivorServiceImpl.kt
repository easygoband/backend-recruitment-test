package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.exception.EntityNotFoundException
import com.eduardo.rdguez.zssn.mapper.SurvivorMapper
import com.eduardo.rdguez.zssn.model.request.LocationRequest
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

  @Transactional(readOnly = true)
  override fun findSurvivorById(id: Long): Survivor {
    return survivorRepository.findByIdAndIsInfectedFalse(id).orElseThrow {
      throw EntityNotFoundException("Survivor with ID: $id not found or is infected")
    }
  }

  @Transactional(propagation = Propagation.REQUIRED)
  override fun saveSurvivor(survivorRequest: SurvivorRequest): SurvivorDto {
    val lastLocation: Location = locationService.saveLocation(survivorRequest.lastLocation)
    val survivor: Survivor = SurvivorMapper.toEntity(survivorRequest, lastLocation)
    val survivorCreated: Survivor = survivorRepository.save(survivor)
    survivorInventoryService.assignInventory(survivorCreated, survivorRequest.items)
    return SurvivorMapper.toDetailedDto(survivorCreated)
  }

  @Transactional(propagation = Propagation.REQUIRED)
  override fun updateSurvivorLocation(id: Long, locationRequest: LocationRequest): SurvivorDto {
    val survivor: Survivor = findSurvivorById(id)
    with(survivor.lastLocation) {
      latitude = locationRequest.latitude
      longitude = locationRequest.longitude
    }
    return SurvivorMapper.toDetailedDto(survivorRepository.save(survivor))
  }

  @Transactional(propagation = Propagation.MANDATORY)
  override fun updateSurvivorToInfected(id: Long): SurvivorDto {
    val survivor: Survivor = findSurvivorById(id)
    survivor.isInfected = true
    return SurvivorMapper.toDetailedDto(survivorRepository.save(survivor))
  }

}