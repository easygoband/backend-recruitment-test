package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.services.ItemsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemsController {
    @Autowired
    private final ItemsService itemsService;

    @GetMapping
    ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemsService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("{item_id}")
    ResponseEntity<Item> getItem(@PathVariable("item_id") Long id){
        return new ResponseEntity<>(itemsService.getItemById(id), HttpStatus.OK);
    }
}
