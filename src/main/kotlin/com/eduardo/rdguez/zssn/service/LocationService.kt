package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.model.request.LocationRequest

interface LocationService {

  fun saveLocation(locationRequest: LocationRequest): Location

}