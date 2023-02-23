package com.eduardo.rdguez.zssn.mapper

import com.eduardo.rdguez.zssn.domain.InfectionLog
import com.eduardo.rdguez.zssn.domain.Survivor

class InfectionLogMapper {
  companion object {
    fun toEntity(speaker: Survivor, infected: Survivor): InfectionLog {
      return InfectionLog(
        speaker = speaker,
        infected = infected
      )
    }
  }
}