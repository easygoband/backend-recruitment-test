package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.model.request.ExchangeRequest
import com.eduardo.rdguez.zssn.model.response.ExchangeResponse

interface ExchangeService {

  fun exchangeItems(exchangeRequest: ExchangeRequest): ExchangeResponse

}