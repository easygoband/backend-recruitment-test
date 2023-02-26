package com.eduardo.rdguez.zssn.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SurvivorInventoryDto(
  val item: String,
  val quantity: Int
)