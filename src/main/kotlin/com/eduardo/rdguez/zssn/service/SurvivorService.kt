package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest

interface SurvivorService {

  fun saveSurvivor(survivorRequest: SurvivorRequest): SurvivorDto

}