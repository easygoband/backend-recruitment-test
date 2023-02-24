package com.eduardo.rdguez.zssn.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ItemAverage(
  @JsonProperty("name")
  val name: String,

  @JsonProperty("average")
  val average: String
)
