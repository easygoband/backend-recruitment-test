package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.mapper.LocationMapper
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.repository.LocationRepository
import com.eduardo.rdguez.zssn.service.LocationService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class LocationServiceImpl(
  private var locationRepository: LocationRepository
) : LocationService {
  private val logger = KotlinLogging.logger {}

  @Transactional(propagation = Propagation.MANDATORY)
  override fun saveLocation(locationRequest: LocationRequest): Location {
    logger.info { "Save a location by request: $locationRequest" }

    val location: Location = LocationMapper.toEntity(locationRequest)
    return locationRepository.save(location)
  }

}