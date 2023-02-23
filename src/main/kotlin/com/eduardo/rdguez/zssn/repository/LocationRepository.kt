package com.eduardo.rdguez.zssn.repository

import com.eduardo.rdguez.zssn.domain.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long>