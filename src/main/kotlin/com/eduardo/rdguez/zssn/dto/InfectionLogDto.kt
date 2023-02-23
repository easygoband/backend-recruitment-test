package com.eduardo.rdguez.zssn.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class InfectionLogDto(
  val speaker: SurvivorDto,
  val infected: SurvivorDto,
)