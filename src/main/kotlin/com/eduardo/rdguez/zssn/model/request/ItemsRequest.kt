package com.eduardo.rdguez.zssn.model.request

import com.eduardo.rdguez.zssn.constant.ApiConstants
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class ItemsRequest(
  @JsonProperty("water")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val water: Int,

  @JsonProperty("food")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val food: Int,

  @JsonProperty("medication")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val medication: Int,

  @JsonProperty("ammunition")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val ammunition: Int
)