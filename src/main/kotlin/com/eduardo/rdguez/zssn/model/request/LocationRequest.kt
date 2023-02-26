package com.eduardo.rdguez.zssn.model.request

import com.eduardo.rdguez.zssn.constant.ApiConstants
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull

data class LocationRequest(
  @JsonProperty("latitude")
  @field:NotNull
  @field:DecimalMin(value = ApiConstants.MIN_LATITUDE, inclusive = true)
  @field:DecimalMax(value = ApiConstants.MAX_LATITUDE, inclusive = true)
  val latitude: Double,

  @JsonProperty("longitude")
  @field:DecimalMin(value = ApiConstants.MIN_LONGITUDE, inclusive = true)
  @field:DecimalMax(value = ApiConstants.MAX_LONGITUDE, inclusive = true)
  @field:NotNull
  val longitude: Double,
)
