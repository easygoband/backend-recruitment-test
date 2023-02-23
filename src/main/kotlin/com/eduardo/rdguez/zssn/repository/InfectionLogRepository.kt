package com.eduardo.rdguez.zssn.repository

import com.eduardo.rdguez.zssn.domain.InfectionLog
import com.eduardo.rdguez.zssn.domain.InfectionLogId
import com.eduardo.rdguez.zssn.domain.Survivor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InfectionLogRepository : CrudRepository<InfectionLog, InfectionLogId> {

  fun countByInfected(infected: Survivor): Long

}