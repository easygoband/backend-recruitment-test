package com.eduardo.rdguez.zssn.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ItemDto(
  val id: Long,
  val name: String,
  val points: Int
)