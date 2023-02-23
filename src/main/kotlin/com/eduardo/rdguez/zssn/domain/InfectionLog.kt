package com.eduardo.rdguez.zssn.domain

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "infection_log")
@IdClass(InfectionLogId::class)
class InfectionLog(
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "speaker_id", referencedColumnName = "id", nullable = false)
  var speaker: Survivor,

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "infected_id", referencedColumnName = "id", nullable = false)
  var infected: Survivor,
)