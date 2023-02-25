package com.eduardo.rdguez.zssn.model.request

import com.eduardo.rdguez.zssn.constant.ApiConstants
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class ItemsRequest(
  @JsonProperty("water")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val water: Int?,

  @JsonProperty("food")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val food: Int?,

  @JsonProperty("medication")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val medication: Int?,

  @JsonProperty("ammunition")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  val ammunition: Int?
) {
  operator fun get(key: String): Int {
    return when (key.lowercase()) {
      "water" -> water!!
      "food" -> food!!
      "medication" -> medication!!
      "ammunition" -> ammunition!!
      else -> 0
    }
  }
}