package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.TradeRequest;
import com.easygo.david.easygotest.exceptions.ApiRequestException;
import com.easygo.david.easygotest.models.InfectedRegister;
import com.easygo.david.easygotest.models.Survivor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TradeServiceTest {

    @Mock
    private InventoriesService inventoriesService;

    @Mock
    private InfectedRegisterService infectedRegisterService;

    @InjectMocks
    private TradeService tradeService;

    InfectedRegister infectedRegister;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        infectedRegister = new InfectedRegister(new Survivor());
    }

    @Test
    void exchangeInfectedUser() {
        infectedRegister.setInfected(true);
        when(infectedRegisterService.findBySurvivorId(any(UUID.class))).thenReturn(infectedRegister);
        assertThrows(ApiRequestException.class, () ->
                tradeService.exchange(UUID.randomUUID(),UUID.randomUUID(), new TradeRequest()));
    }

//    @Test
//    void exchangeTotalNotEqual() {
//        infectedRegister.setInfected(false);
//        when(infectedRegisterService.findBySurvivorId(any(UUID.class))).thenReturn(infectedRegister);
//
//        SurvivorInventory inventory1 = new SurvivorInventory();
//        SurvivorInventory inventory2 = new SurvivorInventory();
//
//        Set<InventoryItemRecord> set1 = new HashSet<>();
//        Set<InventoryItemRecord> set2 = new HashSet<>();
//        set1.add(new InventoryItemRecord(100,new Item(),inventory1));
//        set2.add(new InventoryItemRecord(200,new Item(),inventory1));
//        inventory1.setInventory_item(set1);
//        inventory2.setInventory_item(set2);
//
//        when(inventoriesService.getSingleInventory(any(UUID.class))).thenReturn(inventory1);
//        when(inventoriesService.getSingleInventory(any(UUID.class))).thenReturn(inventory2);
//    }
}