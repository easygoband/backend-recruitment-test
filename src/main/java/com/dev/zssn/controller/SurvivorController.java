package com.dev.zssn.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.zssn.models.Survivor;
import com.dev.zssn.repository.SurvivorRepository;

@RestController
class SurvivorController {

  private final SurvivorRepository repository;

  SurvivorController(SurvivorRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/survivors")
  public List<Survivor> all() {
    return repository.findAll();
  }

}
