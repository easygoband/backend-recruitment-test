package com.eduardo.rdguez.zssn.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class InfectionsResponse(
  @JsonProperty("survivors")
  val survivors: Int,

  @JsonProperty("infections")
  val infections: Int,

  @JsonProperty("percentage")
  val percentage: String
)