package com.eduardo.rdguez.zssn.mapper

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.dto.LocationDto
import com.eduardo.rdguez.zssn.model.request.LocationRequest

class LocationMapper {
  companion object {
    fun toEntity(locationRequest: LocationRequest): Location {
      return Location(
        latitude = locationRequest.latitude,
        longitude = locationRequest.longitude
      )
    }

    fun toDto(location: Location): LocationDto {
      return LocationDto(
        latitude = location.latitude,
        longitude = location.longitude
      )
    }
  }
}