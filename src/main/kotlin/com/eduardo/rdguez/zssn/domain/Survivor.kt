package com.eduardo.rdguez.zssn.domain

import com.eduardo.rdguez.zssn.model.enums.Gender
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity
@Table(name = "survivor")
class Survivor(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Long? = null,

  @Column(name = "name", nullable = false)
  var name: String,

  @Column(name = "age", nullable = false)
  var age: Int,

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  var gender: Gender,

  @Column(name = "is_infected", nullable = true)
  var isInfected: Boolean? = false,

  @OneToOne(fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "location_id", nullable = false)
  var lastLocation: Location,

  @OneToMany(fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "survivor_id")
  var survivorInventory: List<SurvivorInventory> = listOf(),
)