package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.domain.Survivor

interface InfectionLogService {

  fun saveInfectionLog(speaker: Survivor, infected: Survivor)

}
