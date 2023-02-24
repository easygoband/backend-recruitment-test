package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.ItemAverage
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse

interface ReportService {

  fun findInfections(): InfectionsResponse
  fun findNoInfections(): NoInfectionsResponse

}