package com.eduardo.rdguez.zssn.mapper

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.dto.LocationDto
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest

class SurvivorMapper {
  companion object {
    fun toEntity(survivorRequest: SurvivorRequest, location: Location): Survivor {
      return Survivor(
        name = survivorRequest.name,
        age = survivorRequest.age,
        gender = survivorRequest.gender,
        lastLocation = location
      )
    }

    fun toDto(survivor: Survivor): SurvivorDto {
      return SurvivorDto(
        id = survivor.id!!,
        name = survivor.name,
        age = survivor.age,
        gender = survivor.gender,
        isInfected = survivor.isInfected!!
      )
    }

    fun toDetailedDto(survivor: Survivor): SurvivorDto {
      val lastLocation: LocationDto = LocationMapper.toDto(survivor.lastLocation)

      return SurvivorDto(
        id = survivor.id!!,
        name = survivor.name,
        age = survivor.age,
        gender = survivor.gender,
        isInfected = survivor.isInfected!!,
        lastLocation = lastLocation
      )
    }
  }
}