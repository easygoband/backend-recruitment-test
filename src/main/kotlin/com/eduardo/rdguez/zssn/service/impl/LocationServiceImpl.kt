package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.mapper.LocationMapper
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.repository.LocationRepository
import com.eduardo.rdguez.zssn.service.LocationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class LocationServiceImpl(
  private val locationRepository: LocationRepository
) : LocationService {

  @Transactional(propagation = Propagation.MANDATORY)
  override fun saveLocation(locationRequest: LocationRequest): Location {
    val location: Location = LocationMapper.toEntity(locationRequest)
    return locationRepository.save(location)
  }

}