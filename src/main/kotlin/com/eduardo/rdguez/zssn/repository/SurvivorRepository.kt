package com.eduardo.rdguez.zssn.repository

import com.eduardo.rdguez.zssn.domain.Survivor
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurvivorRepository : JpaRepository<Survivor, Long> {

  fun findAllByIsInfectedTrue(): List<Survivor>
  fun findByIdAndIsInfectedFalse(id: Long): Optional<Survivor>
  fun countByIsInfectedFalse(): Long
  fun countByIsInfectedTrue(): Long

}