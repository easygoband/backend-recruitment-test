package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.domain.Item
import java.util.*

interface ItemService {

  fun findItemByName(name: String): Optional<Item>

}