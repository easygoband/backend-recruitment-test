package com.eduardo.rdguez.zssn.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class AverageItemResponse(
  @JsonProperty("name")
  val name: String,

  @JsonProperty("average")
  val average: Int
)
