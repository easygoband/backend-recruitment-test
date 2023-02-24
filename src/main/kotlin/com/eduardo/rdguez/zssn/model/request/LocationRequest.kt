package com.eduardo.rdguez.zssn.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class LocationRequest(
  @JsonProperty("latitude")
  @field:NotNull
  val latitude: Int,

  @JsonProperty("longitude")
  @field:NotNull
  val longitude: Int,
)
