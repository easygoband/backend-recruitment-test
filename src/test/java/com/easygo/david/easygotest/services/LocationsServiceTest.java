package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateLocationRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.exceptions.ApiRequestException;
import com.easygo.david.easygotest.exceptions.NotFoundException;
import com.easygo.david.easygotest.models.Location;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.LocationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LocationsServiceTest {
    @Mock
    private LocationsRepository locationsRepository;

    @InjectMocks
    private LocationsService locationsService;

    private Location location;
    private NewSurvivorRequest req;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        location = new Location();
        req = new NewSurvivorRequest();
        req.setLatitude(0.0);
        req.setLongitude(0.0);
    }

    @Test
    void getAllLocations() {
        when(locationsRepository.findAll()).thenReturn(Collections.singletonList(location));
        assertNotNull(locationsService.getAllLocations());
    }

    @Test
    void getUserLocation() {
        when(locationsRepository.findById(any(UUID.class))).thenReturn(Optional.of(location));
        assertNotNull(locationsService.getUserLocation(UUID.randomUUID()));
    }

    @Test
    void getUserLocationExceptionTest() {
        when(locationsRepository.findById(any(UUID.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> locationsService.getUserLocation(UUID.randomUUID()));
    }

    @Test
    void registrateLocation() {
        when(locationsRepository.save(any(Location.class))).thenReturn(location);

        assertNotNull(locationsService.registrateLocation(new Survivor(),req));
    }

    @Test
    void registrateLocationExceptionTest() {
        when(locationsRepository.findById(any(UUID.class))).thenThrow(ApiRequestException.class);
        assertThrows(ApiRequestException.class, () -> locationsService.registrateLocation(null,null));
    }


    @Test
    void updateSurvivorLastLocation() {
        when(locationsRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(location));
        when(locationsRepository.save(any(Location.class))).thenReturn(location);
        assertNotNull(locationsService.updateSurvivorLastLocation(UUID.randomUUID(),new UpdateLocationRequest(0.0,0.0)));
    }

    @Test
    void updateSurvivorLocationWrongIdFormat() {
        when(locationsRepository.findById(any(UUID.class))).thenThrow(ApiRequestException.class);
        assertThrows(ApiRequestException.class, () -> locationsService.updateSurvivorLastLocation(UUID.randomUUID(), new UpdateLocationRequest(0.0,0.0)));
    }

    @Test
    void updateSurvivorLocationNotFoundException() {
        when(locationsRepository.save(any(Location.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> locationsService.updateSurvivorLastLocation(UUID.randomUUID(), new UpdateLocationRequest(0.0,0.0)));
    }

    @Test
    void deleteSurvivorLocation() {
        doNothing().when(locationsRepository).deleteById(any(UUID.class));
        locationsService.deleteSurvivorLocation(UUID.randomUUID());
    }

    @Test
    void deleteSurvivorLocationIdFormatTest() {
        doThrow(ApiRequestException.class).when(locationsRepository).deleteById(any(UUID.class));
        assertThrows(ApiRequestException.class, () -> locationsService.deleteSurvivorLocation(UUID.randomUUID()));
    }
}