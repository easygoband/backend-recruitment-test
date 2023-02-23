package com.eduardo.rdguez.zssn.repository

import com.eduardo.rdguez.zssn.domain.Survivor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurvivorRepository : JpaRepository<Survivor, Long>