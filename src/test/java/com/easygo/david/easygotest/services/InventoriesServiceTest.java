package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.models.SurvivorInventory;
import com.easygo.david.easygotest.repositories.InfectedRegisterRepository;
import com.easygo.david.easygotest.repositories.InventoryItemRecordRepository;
import com.easygo.david.easygotest.repositories.SurvivorInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class InventoriesServiceTest {

    @InjectMocks
    private  ItemsService itemsService;
    @Mock
    private  SurvivorInventoryRepository survivorInventoryRepository;
    @Mock
    private  InventoryItemRecordRepository inventoryItemRecordRepository;
    @InjectMocks
    private  InfectedRegisterService infectedRegisterService;
    @Mock
    private  InfectedRegisterRepository infectedRegisterRepository;

    @InjectMocks
    private InventoriesService inventoriesService;

    SurvivorInventory survivorInventory;
    UUID uuid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        survivorInventory = new SurvivorInventory(new Survivor());
        survivorInventory.setInventory_item(new HashSet<>());

        uuid = UUID.randomUUID();
    }

    @Test
    void getAllInventories() {
        when(survivorInventoryRepository.findAll()).thenReturn(Collections.singletonList(survivorInventory));
        assertNotNull(inventoriesService.getAllInventories());
    }

    @Test
    void getAllInventoriesCustom() {
        when(survivorInventoryRepository.findByInfectedSurvivor(true)).thenReturn(Collections.singletonList(survivorInventory));
        assertNotNull(inventoriesService.getAllInventoriesCustom(true));
    }

    @Test
    void getSingleInventory() {
        when(survivorInventoryRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(survivorInventory));
        assertNotNull(inventoriesService.getSingleInventory(UUID.randomUUID()));
    }

    @Test
    void updateInventoryValues() {
        when(survivorInventoryRepository.save(any(SurvivorInventory.class))).thenReturn(survivorInventory);
        assertNotNull(inventoriesService.updateInventoryValues(survivorInventory));
    }

    @Test
    void deleteInventory() {
        when(survivorInventoryRepository.findById(any(UUID.class))).thenReturn(Optional.of(survivorInventory));
        doNothing().when(survivorInventoryRepository).deleteById(any(UUID.class));
        doNothing().when(inventoryItemRecordRepository).deleteById(any(Long.class));
        inventoriesService.deleteInventory(UUID.randomUUID());
    }
}