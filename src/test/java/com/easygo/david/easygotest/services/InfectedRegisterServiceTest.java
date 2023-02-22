package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.exceptions.ApiRequestException;
import com.easygo.david.easygotest.exceptions.NotFoundException;
import com.easygo.david.easygotest.models.InfectedRegister;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.InfectedRegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InfectedRegisterServiceTest {

    @Mock
    private InfectedRegisterRepository repository;

    @InjectMocks
    private InfectedRegisterService service;

    InfectedRegister infectedRegister;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        infectedRegister = new InfectedRegister();
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Collections.singletonList(infectedRegister));
        assertNotNull(service.findAll());
    }

    @Test
    void findCustom() {
        when(repository.findAll()).thenReturn(Collections.singletonList(infectedRegister));
        assertNotNull(service.findCustom(true));
    }

    @Test
    void findBySurvivorId() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(infectedRegister));
        assertNotNull(service.findBySurvivorId(UUID.randomUUID()));
    }

    @Test
    void findBySurvivorNoSuchElementExceptionTest() {
        when(repository.findById(any(UUID.class))).thenThrow(NoSuchElementException.class);
        assertThrows(NotFoundException.class, () -> service.findBySurvivorId(UUID.randomUUID()));
    }

    @Test
    void findBySurvivorApiRequestExceptionTest() {
        when(repository.findById(any(UUID.class))).thenThrow(ApiRequestException.class);
        assertThrows(ApiRequestException.class, () -> service.findBySurvivorId(UUID.randomUUID()));
    }

    @Test
    void updaterUserInfectedAlerts() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(infectedRegister));
        assertNotNull(service.updaterUserInfectedAlerts(UUID.randomUUID()));
    }

    @Test
    void updaterNoSuchElementExceptionUserInfectedAlerts() {
        when(repository.findById(any(UUID.class))).thenThrow(NoSuchElementException.class);
        assertThrows(NotFoundException.class, () -> service.updaterUserInfectedAlerts(UUID.randomUUID()));
    }

    @Test
    void updaterExceptionUserInfectedAlerts() {
        when(repository.findById(any(UUID.class))).thenThrow(IllegalArgumentException.class);
        assertThrows(ApiRequestException.class, () -> service.updaterUserInfectedAlerts(UUID.randomUUID()));
    }

    @Test
    void createInfectedRegister() {
        when(repository.save(any(InfectedRegister.class))).thenReturn(infectedRegister);
        assertNotNull(service.createInfectedRegister(new Survivor()));
    }

    @Test
    void createInfectedRegisterException() {
        when(repository.save(any(InfectedRegister.class))).thenThrow(IllegalArgumentException.class);
        assertThrows(ApiRequestException.class, () -> service.createInfectedRegister(new Survivor()));
    }

    @Test
    void deleteInfectedRegister() {
        doNothing().when(repository).deleteById(any(UUID.class));
        service.deleteInfectedRegister(UUID.randomUUID());
    }

    @Test
    void deleteExceptionRegister() {
        doThrow(IllegalArgumentException.class).when(repository).deleteById(any(UUID.class));
        assertThrows(ApiRequestException.class, () -> service.deleteInfectedRegister(UUID.randomUUID()));
    }
}