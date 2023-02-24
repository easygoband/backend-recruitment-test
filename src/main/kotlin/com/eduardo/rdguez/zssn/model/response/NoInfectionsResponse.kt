package com.eduardo.rdguez.zssn.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class NoInfectionsResponse(
  @JsonProperty("survivors")
  val survivors: Long,

  @JsonProperty("no_infections")
  val noInfections: Long,

  @JsonProperty("percentage")
  val percentage: String
)
