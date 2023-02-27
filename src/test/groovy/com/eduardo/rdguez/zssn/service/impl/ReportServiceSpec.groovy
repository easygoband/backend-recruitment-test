package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.enums.Gender
import com.eduardo.rdguez.zssn.model.response.AverageItemResponse
import com.eduardo.rdguez.zssn.model.response.InfectionsResponse
import com.eduardo.rdguez.zssn.model.response.LostPointsResponse
import com.eduardo.rdguez.zssn.model.response.NoInfectionsResponse
import com.eduardo.rdguez.zssn.service.ReportService
import com.eduardo.rdguez.zssn.service.SurvivorInventoryService
import com.eduardo.rdguez.zssn.service.SurvivorService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

@SpringBootTest
@ActiveProfiles("default")
@Unroll
class ReportServiceSpec extends Specification {
  private ReportService reportService

  def setup() {
    reportService = new ReportServiceImpl(
      Mock(SurvivorInventoryService),
      Mock(SurvivorService)
    )
  }

  Should "Spec 1 find infected survivors percentage"() {
    given:
    SurvivorService survivorService = Mock(SurvivorService)
    survivorService.countAllSurvivors() >> survivors
    survivorService.countAllInfectedSurvivors() >> infectedSurvivors
    reportService.survivorService = survivorService
    when:
    InfectionsResponse response = reportService.findInfectedSurvivorsPercentage()
    then:
    assert response.percentage == infectionsResponse.percentage
    assert response.survivors == infectionsResponse.survivors
    assert response.infections == infectionsResponse.infections
    where:
    survivors << [10, 50]
    infectedSurvivors << [1, 15]
    infectionsResponse << [
      new InfectionsResponse(10L, 1L, "10.00"),
      new InfectionsResponse(50L, 15L, "30.00")
    ]
  }

  Should "Spec 2 find uninfected survivors percentage"() {
    given:
    SurvivorService survivorService = Mock(SurvivorService)
    survivorService.countAllSurvivors() >> survivors
    survivorService.countAllUninfectedSurvivors() >> uninfectedSurvivors
    reportService.survivorService = survivorService
    when:
    NoInfectionsResponse response = reportService.findUninfectedSurvivorsPercentage()
    then:
    assert response.percentage == noInfectionsResponse.percentage
    assert response.survivors == noInfectionsResponse.survivors
    assert response.noInfections == noInfectionsResponse.noInfections
    where:
    survivors << [10, 50]
    uninfectedSurvivors << [9, 25]
    noInfectionsResponse << [
      new NoInfectionsResponse(10L, 9L, "90.00"),
      new NoInfectionsResponse(50L, 25L, "50.00")
    ]
  }

  Should "Spec 3 find lost points by survivors"() {
    given:
    SurvivorService survivorService = Mock(SurvivorService)
    survivorService.findAllInfectedSurvivors() >> infected
    reportService.survivorService = survivorService
    when:
    List<LostPointsResponse> response = reportService.findLostPointsByInfectedSurvivor()
    then:
    assert response.lostPoints.sum() == lostPointsResponse.lostPoints.sum()
    assert response.size() == lostPointsResponse.size()
    where:
    infected << [
      [
        new Survivor(3L, "someone", 20, Gender.M, true, new Location(), [
          new SurvivorInventory(1, survivorInfected, new Item(1, "item", 4), 2)
        ]),
        new Survivor(4L, "someone", 21, Gender.F, true, new Location(), [
          new SurvivorInventory(1, survivorInfected, new Item(1, "item", 3), 4)
        ]),
      ]
    ]
    lostPointsResponse << [
      [
        new LostPointsResponse(
          new SurvivorDto(3L, "someone", 20, Gender.M, true, null),
          8
        ),
        new LostPointsResponse(
          new SurvivorDto(4L, "someone", 21, Gender.F, true, null),
          12
        )
      ]
    ]
  }

  Should "Spec 4 find average items by survivor"() {
    given:
    SurvivorInventoryService survivorInventoryService = Mock(SurvivorInventoryService)
    SurvivorService survivorService = Mock(SurvivorService)

    survivorInventoryService.findAllSurvivorInventory() >> survivorInventory
    survivorService.countAllUninfectedSurvivors() >> uninfectedSurvivors

    reportService.survivorInventoryService = survivorInventoryService
    reportService.survivorService = survivorService
    when:
    List<AverageItemResponse> averageItems = reportService.findAverageItemsBySurvivor()
    then:
    assert averageItems == itemAverageResponse
    where:
    survivorInventory << [
      [
        new SurvivorInventory(1, survivorA, new Item(1, "water", 4), 10),
        new SurvivorInventory(2, survivorA, new Item(2, "food", 2), 10),
        new SurvivorInventory(3, survivorA, new Item(3, "medication", 2), 10),
        new SurvivorInventory(4, survivorA, new Item(4, "ammunition", 1), 2),
        new SurvivorInventory(5, survivorB, new Item(1, "water", 4), 15),
        new SurvivorInventory(6, survivorB, new Item(2, "food", 2), 10),
        new SurvivorInventory(7, survivorB, new Item(3, "medication", 2), 10),
        new SurvivorInventory(8, survivorB, new Item(4, "ammunition", 1), 2)
      ]
    ]
    uninfectedSurvivors << [2]
    itemAverageResponse << [
      [
        new AverageItemResponse("water", 12),
        new AverageItemResponse("food", 10),
        new AverageItemResponse("medication", 10),
        new AverageItemResponse("ammunition", 2)
      ]
    ]
  }

  @Shared
  Survivor survivorInfected = new Survivor(1L, "someone", 20, Gender.M, true, new Location(), [])
  @Shared
  Survivor survivorA = new Survivor(1L, "someone", 20, Gender.F, false, new Location(), [])
  @Shared
  Survivor survivorB = new Survivor(2L, "someone", 21, Gender.F, false, new Location(), [])
}
