package com.eduardo.rdguez.zssn.repository

import com.eduardo.rdguez.zssn.domain.Item
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Long> {

  fun findByNameIgnoreCase(name: String): Optional<Item>

}