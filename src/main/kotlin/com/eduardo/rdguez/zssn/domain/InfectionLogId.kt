package com.eduardo.rdguez.zssn.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class InfectionLogId(
  var speaker: Long,
  var infected: Long
) : Serializable