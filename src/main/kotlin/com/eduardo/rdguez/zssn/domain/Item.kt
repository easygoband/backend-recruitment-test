package com.eduardo.rdguez.zssn.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "item")
class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Long? = null

  @Column(name = "name", nullable = false)
  lateinit var name: String

  @Column(name = "points", nullable = false)
  var points: Int = 0
}