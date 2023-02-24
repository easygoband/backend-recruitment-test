package com.eduardo.rdguez.zssn.model.response

import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.fasterxml.jackson.annotation.JsonProperty

data class LostPointsResponse(
  @JsonProperty("infected")
  val infected: SurvivorDto,

  @JsonProperty("lost_points")
  val lostPoints: Int
)
