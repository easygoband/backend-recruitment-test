package com.easy.zssn.controller;

import java.util.List;

import com.easy.zssn.Objects.SurvivorRO;
import com.easy.zssn.model.Inventory;
import com.easy.zssn.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/inventory")
public class InventoryController {
    @Autowired
    public InventoryService inventoryService;
    
    /** CRUD Inventory */
    @PostMapping
    public @ResponseBody Inventory upsertInventory(
        @RequestBody SurvivorRO newObj
    ){
        return inventoryService.upsertInventory(newObj.getNewInventory());
    }

    @DeleteMapping
    public @ResponseBody String deleteInventory(
        @RequestParam int id
    ){  String resultado = "";
        if(inventoryService.deleteInventory(id))
            resultado = "Inventory deleted correctly";
        else
            resultado = "Error trying to delete Inventory";
        return resultado;
    }

    @GetMapping(value="/findbyid")
    public @ResponseBody Inventory findbyidInventory(
        @RequestParam int id
    ){
        return inventoryService.findByIDInventory(id);
    }

    @GetMapping(value="/findbySurvivorId")
    public @ResponseBody List<Inventory> findbySurvivorId(
        @RequestParam int id
    ){
        return inventoryService.findAllBySurvivorID(id);
    }

    @GetMapping
    public @ResponseBody List<Inventory> findallInventory(
    ){
        return inventoryService.findAllInventory();
    }
}
