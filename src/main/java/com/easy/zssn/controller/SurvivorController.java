package com.easy.zssn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/survivor")
public class SurvivorController {
    @GetMapping(value="/path")
    public @ResponseBody String getMethodName(@RequestParam String param) {
        return "Hola "+param;
    }
    
}
