package com.eduardo.rdguez.zssn.model.response

import java.io.Serializable
import org.springframework.http.HttpStatus

data class ApiErrorResponse(
  val status: HttpStatus,
  val message: String
) : Serializable {
  constructor(status: HttpStatus, ex: Throwable) : this(status, ex.message.toString())
}