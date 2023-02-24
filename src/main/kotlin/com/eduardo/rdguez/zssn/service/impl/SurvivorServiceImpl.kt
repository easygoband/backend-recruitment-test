package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.exception.NotFoundException
import com.eduardo.rdguez.zssn.mapper.SurvivorMapper
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest
import com.eduardo.rdguez.zssn.repository.SurvivorRepository
import com.eduardo.rdguez.zssn.service.LocationService
import com.eduardo.rdguez.zssn.service.SurvivorInventoryService
import com.eduardo.rdguez.zssn.service.SurvivorService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class SurvivorServiceImpl(
  private val survivorRepository: SurvivorRepository,
  private val survivorInventoryService: SurvivorInventoryService,
  private val locationService: LocationService
) : SurvivorService {
  private val logger = KotlinLogging.logger {}

  @Transactional(readOnly = true)
  override fun findAllInfectedSurvivors(): List<Survivor> {
    logger.info { "Find all infected survivors" }

    return survivorRepository.findAllByIsInfectedTrue()
  }

  @Transactional(readOnly = true)
  override fun findSurvivorById(id: Long): Survivor {
    logger.info { "Find survivor by ID: $id" }

    return survivorRepository.findByIdAndIsInfectedFalse(id).orElseThrow {
      throw NotFoundException("Survivor with ID: $id not found or is infected")
    }
  }

  override fun countAllSurvivors(): Long {
    logger.info { "Count all survivors" }

    return survivorRepository.count()
  }

  @Transactional(readOnly = true)
  override fun countAllUninfectedSurvivors(): Long {
    logger.info { "Count all uninfected survivors" }

    return survivorRepository.countByIsInfectedFalse()
  }

  @Transactional(readOnly = true)
  override fun countAllInfectedSurvivors(): Long {
    logger.info { "Count all infected survivors" }

    return survivorRepository.countByIsInfectedTrue()
  }

  @Transactional(propagation = Propagation.REQUIRED)
  override fun saveSurvivor(survivorRequest: SurvivorRequest): SurvivorDto {
    logger.info { "Save a survivor by request: $survivorRequest" }

    val lastLocation: Location = locationService.saveLocation(survivorRequest.lastLocation)
    val survivor: Survivor = SurvivorMapper.toEntity(survivorRequest, lastLocation)
    val survivorCreated: Survivor = survivorRepository.save(survivor)
    survivorInventoryService.assignInventory(survivorCreated, survivorRequest.items)
    return SurvivorMapper.toDetailedDto(survivorCreated)
  }

  @Transactional(propagation = Propagation.REQUIRED)
  override fun updateSurvivorLocation(id: Long, locationRequest: LocationRequest): SurvivorDto {
    logger.info { "Update survivor location by request: $locationRequest" }

    val survivor: Survivor = findSurvivorById(id)
    with(survivor.lastLocation) {
      latitude = locationRequest.latitude
      longitude = locationRequest.longitude
    }
    return SurvivorMapper.toDetailedDto(survivorRepository.save(survivor))
  }

  @Transactional(propagation = Propagation.MANDATORY)
  override fun updateSurvivorToInfected(id: Long): SurvivorDto {
    logger.info { "Update survivor with ID: $id to infected" }

    val survivor: Survivor = findSurvivorById(id)
    survivor.isInfected = true
    return SurvivorMapper.toDetailedDto(survivorRepository.save(survivor))
  }

}