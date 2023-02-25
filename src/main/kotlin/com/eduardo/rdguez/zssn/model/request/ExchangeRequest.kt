package com.eduardo.rdguez.zssn.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class ExchangeRequest(
  @JsonProperty("sender_id")
  @field:NotNull
  val senderId: Long,

  @JsonProperty("receiver_id")
  @field:NotNull
  val receiverId: Long,

  @JsonProperty("sender_items")
  @field:Valid
  @field:NotNull
  val senderItems: ItemsRequest,

  @JsonProperty("receiver_items")
  @field:Valid
  @field:NotNull
  val receiverItems: ItemsRequest,
)