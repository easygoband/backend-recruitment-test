package com.eduardo.rdguez.zssn.service

import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.AverageItemResponse
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse

interface ReportService {

  fun findInfectedSurvivorsPercentage(): InfectionsResponse
  fun findUninfectedSurvivorsPercentage(): NoInfectionsResponse
  fun findLostPointsByInfectedSurvivor(): List<LostPointsResponse>
  fun findAverageItemsBySurvivor(): List<AverageItemResponse>

}