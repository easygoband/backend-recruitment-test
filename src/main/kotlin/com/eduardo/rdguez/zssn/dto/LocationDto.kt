package com.eduardo.rdguez.zssn.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LocationDto(
  val latitude: Int,
  val longitude: Int,
)
