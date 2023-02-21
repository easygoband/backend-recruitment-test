package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.services.ItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/survivors/items")
@Tag(name = "Items Controller", description = "This Controller contains the endpoint getting the registered items for trade.")
@AllArgsConstructor
public class ItemsController {
    @Autowired
    private final ItemsService itemsService;

    @GetMapping
    @Operation(summary = "Get all registered Items", description = "This endpoint returns a complete list of all items in database.")
    ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemsService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("{item_id}")
    @Operation(summary = "Get a single Item by ID", description = "This endpoint returns an Item based on the ID.")
    ResponseEntity<Item> getItem(@PathVariable("item_id") Long id){
        return new ResponseEntity<>(itemsService.getItemById(id), HttpStatus.OK);
    }
}
