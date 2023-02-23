package com.eduardo.rdguez.zssn.model.request

import com.eduardo.rdguez.zssn.constant.ApiConstants
import com.eduardo.rdguez.zssn.model.enums.Gender
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SurvivorRequest(
  @JsonProperty("name")
  @field:NotBlank
  @field:Pattern(regexp = ApiConstants.LETTERS_REGEX)
  @field:Size(min = ApiConstants.TWO, max = ApiConstants.FIFTY)
  val name: String,

  @JsonProperty("age")
  @field:NotNull
  @field:Min(ApiConstants.ZERO.toLong())
  @field:Max(ApiConstants.ONE_HUNDRED_TWENTY_FIVE)
  var age: Int,

  @JsonProperty("gender")
  @field:NotNull
  val gender: Gender = Gender.M,

  @JsonProperty("last_location")
  @field:NotNull
  val lastLocation: LocationRequest,

  @JsonProperty("items")
  @field:NotNull
  val items: ItemsRequest
)