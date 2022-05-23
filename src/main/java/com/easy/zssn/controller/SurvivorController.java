package com.easy.zssn.controller;

import java.util.List;

import com.easy.zssn.model.Survivor;
import com.easy.zssn.requestObjects.SurvivorRO;
import com.easy.zssn.service.SurvivorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/survivor")
public class SurvivorController {
    @Autowired
    public SurvivorService survivorService;
    
    /*
        CRUD Survivor
     */
    @PostMapping(value="/")
    public @ResponseBody Survivor upsertSurvivor(
        @RequestBody SurvivorRO newObj
    ){
        return survivorService.upsertSurvivor(newObj.getNewSurvivor());
    }

    @DeleteMapping(value="/")
    public @ResponseBody String deleteSurvivor(
        @RequestParam int id
    ){  String resultado = "";
        if(survivorService.deleteSurvivor(id))
            resultado = "Survivor deleted correctly";
        else
            resultado = "Error trying to delete Survivor";
        return resultado;
    }

    @GetMapping(value="/findbyidSurvivor")
    public @ResponseBody Survivor findbySurvivor(
        @RequestParam int id
    ){
        return survivorService.findByIDSurvivor(id);
    }

    @GetMapping(value="/")
    public @ResponseBody List<Survivor> findall(
    ){
        return survivorService.findAllSurvivor();
    }

    
    //-----------------------------------------------
    
}
