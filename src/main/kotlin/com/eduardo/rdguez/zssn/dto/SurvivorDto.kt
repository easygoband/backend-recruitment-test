package com.eduardo.rdguez.zssn.dto

import com.eduardo.rdguez.zssn.model.enums.Gender
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class SurvivorDto(
  val id: Long,
  val name: String,
  val age: Int,
  val gender: Gender,
  var isInfected: Boolean,
  @JsonProperty("last_location")
  val lastLocation: LocationDto? = null,
)