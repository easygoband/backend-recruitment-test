package com.easy.zssn.controller;

import com.easy.zssn.model.Survivor;
import com.easy.zssn.requestObjects.SurvivorRO;
import com.easy.zssn.service.SurvivorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/survivor")
public class SurvivorController {
    @Autowired
    public SurvivorService survivorService;



    @PostMapping(value="/add")
    public @ResponseBody String getMethodName(
        @RequestBody SurvivorRO newObj
            //String newString
    ) {
        Survivor newSurvivor = survivorService.addSurvivor(newObj.newSurvivor);
        return "Hola "+newSurvivor.getName().toString()+" you are added!";
    }
    
}
