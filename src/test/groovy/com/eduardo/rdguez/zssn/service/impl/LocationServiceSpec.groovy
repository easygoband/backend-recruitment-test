package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Location
import com.eduardo.rdguez.zssn.dto.LocationDto
import com.eduardo.rdguez.zssn.model.request.LocationRequest
import com.eduardo.rdguez.zssn.repository.LocationRepository
import com.eduardo.rdguez.zssn.service.LocationService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

@SpringBootTest
@ActiveProfiles("default")
@Unroll
class LocationServiceSpec extends Specification {
  private LocationService locationService

  def setup() {
    locationService = new LocationServiceImpl(Mock(LocationRepository))
  }

  @Transactional
  @Rollback(true)
  Should "Spec 1 save a location"() {
    given:
    def locationRepository = Mock(LocationRepository)
    locationRepository.save(_ as Location) >> savedLocation
    locationService.locationRepository = locationRepository
    when:
    Location location = locationService.saveLocation(locationRequest)
    then:
    assert location.id
    assert location.latitude == expectedLocation.latitude
    assert location.longitude == expectedLocation.longitude
    where:
    locationRequest << [
      new LocationRequest(37.7749, 122.4194),
      new LocationRequest(-37.7749, -122.4194),
      new LocationRequest(-37.7749, 122.4194),
      new LocationRequest(37.7749, -122.4194),
    ]
    savedLocation << [
      new Location(1, 37.7749, 122.4194),
      new Location(2, -37.7749, -122.4194),
      new Location(3, -37.7749, 122.4194),
      new Location(4, 37.7749, -122.4194),
    ]
    expectedLocation << [
      new LocationDto(37.7749, 122.4194),
      new LocationDto(-37.7749, -122.4194),
      new LocationDto(-37.7749, 122.4194),
      new LocationDto(37.7749, -122.4194),
    ]
  }
}
