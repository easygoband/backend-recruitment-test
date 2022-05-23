package com.easy.zssn.controller;

import java.util.List;

import com.easy.zssn.Objects.SurvivorRO;
import com.easy.zssn.model.Location;
import com.easy.zssn.service.LocationService;

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
@RequestMapping(path="/location")
public class LocationController {
    @Autowired
    public LocationService locationService;


    /**
     * CRUD Location
     */
    @PostMapping(value="/")
    public @ResponseBody Location upsertLocation(
        @RequestBody SurvivorRO newObj
    ){
        return locationService.upsertLocation(newObj.getNewLocation());
    }

    @DeleteMapping(value="/")
    public @ResponseBody String deleteLocation(
        @RequestParam int id
    ){  String resultado = "";
        if(locationService.deleteLocation(id))
            resultado = "Survivor deleted correctly";
        else
            resultado = "Error trying to delete Survivor";
        return resultado;
    }

    @GetMapping(value="/findbyidLocation")
    public @ResponseBody Location findbyidLocation(
        @RequestParam int id
    ){
        return locationService.findByIDLocation(id);
    }

    @GetMapping(value="/")
    public @ResponseBody List<Location> findallLocation(
    ){
        return locationService.findAllLocation();
    }
}
