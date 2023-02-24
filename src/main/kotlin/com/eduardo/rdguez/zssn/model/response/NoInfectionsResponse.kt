package com.eduardo.rdguez.zssn.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class NoInfectionsResponse(
  @JsonProperty("no_infections")
  val noInfections: Int,

  @JsonProperty("percentage")
  val percentage: String
)
