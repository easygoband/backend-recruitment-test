package com.dev.zssn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.zssn.dto.ReportDto;
import com.dev.zssn.services.ReportService;

@RestController
public class ReportsController {

  final Logger LOGGER = LoggerFactory.getLogger(SurvivorController.class);

  private final ReportService service;
  
  public ReportsController(final ReportService service) {
    this.service = service;
  }

  @GetMapping("/report")
  public ResponseEntity<ReportDto> report() {
    try {
      final ReportDto report = this.service.report();
      return new ResponseEntity<>(report, HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
