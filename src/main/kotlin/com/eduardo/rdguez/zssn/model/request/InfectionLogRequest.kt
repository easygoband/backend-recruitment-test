package com.eduardo.rdguez.zssn.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.AssertTrue

data class InfectionLogRequest(
  @JsonProperty("speaker_id")
  val speakerId: Long,

  @JsonProperty("infected_id")
  val infectedId: Long,
) {
  @AssertTrue(message = "Survivors must be different")
  fun isSpeakerIdNotEqualToInfectedId(): Boolean {
    return speakerId != infectedId
  }
}
