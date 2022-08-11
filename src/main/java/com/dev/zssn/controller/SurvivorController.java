package com.dev.zssn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.zssn.dto.PositionDto;
import com.dev.zssn.dto.SurvivorDto;
import com.dev.zssn.services.SurvivorService;

@RestController
class SurvivorController {

  final Logger LOGGER = LoggerFactory.getLogger(SurvivorController.class);

  private final SurvivorService service;

  SurvivorController(SurvivorService repository) {
    this.service = repository;
  }

  @GetMapping("/survivors")
  public ResponseEntity<List<SurvivorDto>> all() {
    try {
      final List<SurvivorDto> survivors = service.all();
      return new ResponseEntity<>(survivors, HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/survivors")
  public ResponseEntity<SurvivorDto> registerSurvivor(@RequestBody SurvivorDto survivor) {
    try {
      final SurvivorDto savedSurvivor = service.registerSurvivor(survivor);
      return new ResponseEntity<>(savedSurvivor, HttpStatus.CREATED);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/update-position/{survivorId}")
  public ResponseEntity<PositionDto> updateSurvivorPosition(
    @PathVariable final Long survivorId,
    @RequestBody final PositionDto position
  ) {
    try {
      final PositionDto positionDto = this.service.updateSurvivorPosition(survivorId, position);
      return new ResponseEntity<>(positionDto, HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
