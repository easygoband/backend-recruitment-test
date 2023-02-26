package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.InfectionLog
import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.model.enums.Gender
import com.eduardo.rdguez.zssn.repository.InfectionLogRepository
import com.eduardo.rdguez.zssn.service.InfectionLogService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

@SpringBootTest
@ActiveProfiles("default")
@Unroll
class InfectionLogServiceSpec extends Specification {
  private InfectionLogService infectionLogService

  def setup() {
    infectionLogService = new InfectionLogServiceImpl(Mock(InfectionLogRepository))
  }

  Should "Spec 1 save infection log"() {
    given:
    InfectionLogRepository infectionLogRepository = Mock(InfectionLogRepository)
    infectionLogRepository.save(_ as InfectionLog) >> infectionLog
    infectionLogService.infectionLogRepository = infectionLogRepository
    when:
    infectionLogService.saveInfectionLog(infectionLog.speaker, infectionLog.infected)
    then:
    noExceptionThrown()
    where:
    infectionLog << [
      new InfectionLog(
        new Survivor(1L, "someone", 18, Gender.M, false, new Location(), []),
        new Survivor(2L, "someone", 19, Gender.F, false, new Location(), []),
      ),
      new InfectionLog(
        new Survivor(1L, "someone", 18, Gender.M, false, new Location(), []),
        new Survivor(3L, "someone", 20, Gender.F, false, new Location(), []),
      )
    ]
  }

  Should "Spec 2 count logs by survivor"() {
    given:
    InfectionLogRepository infectionLogRepository = Mock(InfectionLogRepository)
    infectionLogRepository.countByInfected(_ as Survivor) >> totalLogs
    infectionLogService.infectionLogRepository = infectionLogRepository
    when:
    Long logs = infectionLogService.countLogsBySurvivor(infected)
    then:
    assert logs == totalLogs
    where:
    totalLogs << [
      2L,
      3L,
    ]
    infected << [
      new Survivor(3L, "someone", 20, Gender.M, false, new Location(), []),
      new Survivor(4L, "someone", 21, Gender.F, false, new Location(), []),
    ]
  }
}
