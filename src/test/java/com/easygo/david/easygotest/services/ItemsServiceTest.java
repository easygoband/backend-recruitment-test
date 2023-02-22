package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.exceptions.NotFoundException;
import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.repositories.ItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ItemsServiceTest {

    @Mock
    private ItemsRepository itemsRepository;

    @InjectMocks
    private ItemsService itemsService;

    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        item = new Item("object", 10);
    }

    @Test
    void getAllItems() {
        when(itemsRepository.findAll()).thenReturn(Collections.singletonList(item));
        assertNotNull(itemsService.getAllItems());
    }

    @Test
    void getItemById() {
        when(itemsRepository.findById(item.getItem_id())).thenReturn(Optional.of(item));
        assertNotNull(itemsService.getItemById(item.getItem_id()));
    }

    @Test
    void getItemByIdExceptionTest() {
        when(itemsRepository.findById(any(Long.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> itemsService.getItemById(item.getItem_id()));
    }

    @Test
    void findByName() {
        when(itemsRepository.findByName(any(String.class))).thenReturn(Collections.singletonList(item));
        assertNotNull(itemsService.findByName(item.getName()));
    }
}