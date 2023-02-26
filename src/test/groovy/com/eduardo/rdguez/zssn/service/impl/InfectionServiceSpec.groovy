package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.model.enums.Gender
import com.eduardo.rdguez.zssn.model.request.InfectionLogRequest
import com.eduardo.rdguez.zssn.service.InfectionLogService
import com.eduardo.rdguez.zssn.service.InfectionService
import com.eduardo.rdguez.zssn.service.SurvivorService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

@SpringBootTest
@ActiveProfiles("default")
@Unroll
class InfectionServiceSpec extends Specification {
  private InfectionService infectionService

  def setup() {
    infectionService = new InfectionServiceImpl(
      Mock(InfectionLogService),
      Mock(SurvivorService)
    )
  }

  Should "Spec 1 report survivor infection"() {
    given:
    SurvivorService survivorService = Mock(SurvivorService)
    InfectionLogService infectionLogService = Mock(InfectionLogService)

    survivorService.findSurvivorById(infectionLogRequest.speakerId) >> speaker
    survivorService.findSurvivorById(infectionLogRequest.infectedId) >> infected
    infectionLogService.countLogsBySurvivor(_ as Survivor) >> totalLogs

    infectionService.survivorService = survivorService
    infectionService.infectionLogService = infectionLogService
    when:
    infectionService.reportSurvivorInfection(infectionLogRequest)
    then:
    noExceptionThrown()
    where:
    speaker << [
      new Survivor(1L, "someone", 18, Gender.M, false, new Location(), []),
      new Survivor(2L, "someone", 19, Gender.F, false, new Location(), []),
    ]
    infected << [
      new Survivor(3L, "someone", 20, Gender.M, false, new Location(), []),
      new Survivor(4L, "someone", 21, Gender.F, false, new Location(), []),
    ]
    totalLogs << [
      2L,
      3L,
    ]
    infectionLogRequest << [
      new InfectionLogRequest(1, 3),
      new InfectionLogRequest(2, 3),
    ]
  }
}
