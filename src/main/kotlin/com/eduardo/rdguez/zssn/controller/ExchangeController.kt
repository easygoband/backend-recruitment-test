package com.eduardo.rdguez.zssn.controller

import com.eduardo.rdguez.zssn.exception.BadRequestException
import com.eduardo.rdguez.zssn.model.request.ExchangeRequest
import com.eduardo.rdguez.zssn.model.response.ExchangeResponse
import com.eduardo.rdguez.zssn.service.ExchangeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Exchange Controller")
@RequestMapping("/api/v1/exchanges")
class ExchangeController(
  private val exchangeService: ExchangeService
) {

  @PostMapping
  @Operation(summary = "Exchange of items between survivors")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Successful exchange"),
    ]
  )
  fun exchangeItems(
    @Valid @RequestBody exchangeRequest: ExchangeRequest,
    errors: Errors
  ): ExchangeResponse {
    if (errors.hasErrors()) {
      throw BadRequestException("$errors")
    }

    return exchangeService.itemsExchange(exchangeRequest)
  }

}