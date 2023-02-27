package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.domain.Survivor
import com.eduardo.rdguez.zssn.domain.SurvivorInventory
import com.eduardo.rdguez.zssn.dto.SurvivorDto
import com.eduardo.rdguez.zssn.model.enums.Gender
import com.eduardo.rdguez.zssn.model.request.ItemsRequest
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.model.request.SurvivorRequest
import com.eduardo.rdguez.zssn.repository.SurvivorRepository
import com.eduardo.rdguez.zssn.service.LocationService
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
class SurvivorServiceSpec extends Specification {
  private SurvivorService survivorService

  def setup() {
    survivorService = new SurvivorServiceImpl(
      Mock(SurvivorRepository),
      Mock(SurvivorInventoryService),
      Mock(LocationService)
    )
  }

  Should "Spec 1 find all infected survivors"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.findAllByIsInfectedTrue() >> infected
    survivorService.survivorRepository = survivorRepository
    when:
    List<Survivor> infectedSurvivors = survivorService.findAllInfectedSurvivors()
    then:
    assert infectedSurvivors.size() == infected.size()
    where:
    infected << [sharedInfectedSurvivors]
  }

  Should "Spec 2 find survivor by id"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.findByIdAndIsInfectedFalse(_ as Long) >> Optional.of(_survivor)
    survivorService.survivorRepository = survivorRepository
    when:
    Survivor survivor = survivorService.findSurvivorById(_survivorId as Long)
    then:
    assert survivor.id == _survivorId
    where:
    _survivor << sharedUninfectedSurvivors
    _survivorId << [1L, 2L]
  }

  Should "Spec 3 count all survivors"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.count() >> _totalSurvivor
    survivorService.survivorRepository = survivorRepository
    when:
    Long totalSurvivors = survivorService.countAllSurvivors()
    then:
    assert totalSurvivors == _totalSurvivor
    where:
    _totalSurvivor << [4L]
  }

  Should "Spec 4 count all uninfected survivors"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.countByIsInfectedFalse() >> uninfectedSurvivors
    survivorService.survivorRepository = survivorRepository
    when:
    Long totalSurvivors = survivorService.countAllUninfectedSurvivors()
    then:
    assert totalSurvivors == uninfectedSurvivors
    where:
    uninfectedSurvivors << [sharedUninfectedSurvivors.size().toLong()]
  }

  Should "Spec 5 count all uninfected survivors"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.countByIsInfectedTrue() >> infectedSurvivor
    survivorService.survivorRepository = survivorRepository
    when:
    Long totalSurvivors = survivorService.countAllInfectedSurvivors()
    then:
    assert totalSurvivors == infectedSurvivor
    where:
    infectedSurvivor << [sharedInfectedSurvivors.size().toLong()]
  }

  Should "Spec 6 save a survivor"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.save(_ as Survivor) >> _survivor
    survivorService.survivorRepository = survivorRepository
    when:
    Survivor survivor = survivorService.saveSurvivor(_survivor)
    then:
    assert survivor == _survivor
    where:
    _survivor << [sharedUninfectedSurvivors.first()]
  }

  Should "Spec 7 save a survivor by request"() {
    given:
    SurvivorInventoryService survivorInventoryService = Mock(SurvivorInventoryService)
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    LocationService locationService = Mock(LocationService)

    survivorInventoryService.assignInventory(_ as Survivor, _ as ItemsRequest) >> _survivorInventory
    locationService.saveLocation(_ as LocationRequest) >> _survivor.lastLocation
    survivorRepository.save(_ as Survivor) >> _survivor

    survivorService.survivorInventoryService = survivorInventoryService
    survivorService.survivorRepository = survivorRepository
    survivorService.locationService = locationService
    when:
    SurvivorDto survivor = survivorService.saveSurvivor(_survivorRequest)
    then:
    assert survivor.name == _survivorRequest.name
    assert survivor.age == _survivorRequest.age
    assert survivor.gender == _survivorRequest.gender
    assert survivor.lastLocation.longitude == _survivorRequest.lastLocation.longitude
    assert survivor.lastLocation.latitude == _survivorRequest.lastLocation.latitude
    assert !survivor.infected
    where:
    _survivorInventory << [sharedSurvivorInventory]
    _survivor << [sharedUninfectedSurvivors.first()]
    _survivorRequest << [
      new SurvivorRequest(
        "someone", 20, Gender.M, sharedLocationRequest,
        new ItemsRequest(10, 10, 10, 2)
      )
    ]
  }

  Should "Spec 8 update survivor location"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.findByIdAndIsInfectedFalse(_ as Long) >> Optional.of(_survivor)
    survivorRepository.save(_ as Survivor) >> _survivor
    survivorService.survivorRepository = survivorRepository
    when:
    SurvivorDto survivor = survivorService.updateSurvivorLocation(
      _survivor.id,
      _locationRequest
    )
    then:
    assert survivor.id == _survivor.id
    assert survivor.lastLocation.latitude == _locationRequest.latitude
    assert survivor.lastLocation.longitude == _locationRequest.longitude
    where:
    _survivor << [sharedUninfectedSurvivors.first()]
    _locationRequest << [sharedLocationRequest]
  }

  Should "Spec 9 update survivor to infected"() {
    given:
    SurvivorRepository survivorRepository = Mock(SurvivorRepository)
    survivorRepository.findByIdAndIsInfectedFalse(_ as Long) >> Optional.of(_survivor)
    survivorRepository.save(_ as Survivor) >> _survivor
    survivorService.survivorRepository = survivorRepository
    when:
    SurvivorDto survivor = survivorService.updateSurvivorToInfected(_survivor.id)
    then:
    assert survivor.isInfected()
    where:
    _survivor << [sharedUninfectedSurvivors.first()]
  }

  @Shared
  List<Survivor> sharedUninfectedSurvivors = [
    new Survivor(1L, "someone", 20, Gender.M, false, new Location(1, 37.7749, 122.4194), []),
    new Survivor(2L, "someone", 20, Gender.F, false, new Location(), [])
  ]

  @Shared
  List<Survivor> sharedInfectedSurvivors = [
    new Survivor(1L, "someone", 20, Gender.M, true, new Location(), []),
    new Survivor(2L, "someone", 21, Gender.F, true, new Location(), [])
  ]

  @Shared
  List<SurvivorInventory> sharedSurvivorInventory = [
    new SurvivorInventory(4, sharedUninfectedSurvivors.first(), new Item(4, "ammunition", 1), 2),
    new SurvivorInventory(4, sharedUninfectedSurvivors.first(), new Item(4, "ammunition", 1), 2),
  ]

  @Shared
  LocationRequest sharedLocationRequest = new LocationRequest(37.7749, 122.4194)
}
