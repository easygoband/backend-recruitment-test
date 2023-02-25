package com.eduardo.rdguez.zssn.model.response

import com.eduardo.rdguez.zssn.dto.SurvivorInventoryDto
import com.fasterxml.jackson.annotation.JsonProperty

data class ExchangeResponse(
  @JsonProperty("sender_inventory")
  val senderInventory: List<SurvivorInventoryDto>,

  @JsonProperty("receiver_inventory")
  val receiverInventory: List<SurvivorInventoryDto>,
)