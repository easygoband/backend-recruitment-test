package com.dev.zssn.services;

import org.springframework.stereotype.Service;

import com.dev.zssn.repository.SurvivorRepository;

@Service
public class SurvivorService {

  private final SurvivorRepository repository;

  SurvivorService(SurvivorRepository repository) {
    this.repository = repository;
  }
  
}
