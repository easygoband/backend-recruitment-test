package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.exceptions.ApiRequestException;
import com.easygo.david.easygotest.exceptions.NotFoundException;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.SurvivorRepository;
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

class SurvivorsServiceTest {
    @Mock
    private SurvivorRepository survivorRepository;

    @InjectMocks
    private SurvivorsService survivorsService;

    private Survivor survivor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        survivor = new Survivor();
    }

    @Test
    void findAll() {
        when(survivorRepository.findAll()).thenReturn(Collections.singletonList(survivor));
        assertNotNull(survivorsService.findAll());
    }

    @Test
    void findSurvivorById() {
        when(survivorRepository.findById(survivor.getId())).thenReturn(Optional.of(survivor));
        assertNotNull(survivorsService.findSurvivorById(survivor.getId()));
    }

    @Test
    void findSurvivorByIdExceptionTest() {
        when(survivorRepository.findById(any(UUID.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> survivorsService.findSurvivorById(UUID.randomUUID()));
    }

    @Test
    void registrateSurvivor() {
        when(survivorRepository.save(any(Survivor.class))).thenReturn(survivor);
        assertNotNull(survivorsService.registrateSurvivor(new NewSurvivorRequest()));
    }

    @Test
    void updateSurvivorData() {
        when(survivorRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(survivor));
        when(survivorRepository.save(any(Survivor.class))).thenReturn(survivor);
        assertNotNull(survivorsService.updateSurvivorData(UUID.randomUUID(),new UpdateSurvivorRequest()));
    }

    @Test
    void updateSurvivorDataWrongIdFormat() {
        when(survivorRepository.findById(any(UUID.class))).thenThrow(ApiRequestException.class);
        assertThrows(ApiRequestException.class, () -> survivorsService.updateSurvivorData(UUID.randomUUID(),new UpdateSurvivorRequest()));
    }

    @Test
    void updateSurvivorDataNotFoundException() {
        when(survivorRepository.save(any(Survivor.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> survivorsService.updateSurvivorData(UUID.randomUUID(), new UpdateSurvivorRequest()));
    }

    @Test
    void deleteSurvivor() {
        doNothing().when(survivorRepository).deleteById(any(UUID.class));
        survivorsService.deleteSurvivor(UUID.randomUUID());
    }

    @Test
    void deleteSurvivorIdFormatTest() {
        doThrow(ApiRequestException.class).when(survivorRepository).deleteById(any(UUID.class));
        assertThrows(ApiRequestException.class, () -> survivorsService.deleteSurvivor(UUID.randomUUID()));
    }


}