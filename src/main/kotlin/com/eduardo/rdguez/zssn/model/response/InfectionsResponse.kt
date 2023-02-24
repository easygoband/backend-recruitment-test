package com.eduardo.rdguez.zssn.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class InfectionsResponse(
  @JsonProperty("survivors")
  val survivors: Long,

  @JsonProperty("infections")
  val infections: Long,

  @JsonProperty("percentage")
  val percentage: String
)