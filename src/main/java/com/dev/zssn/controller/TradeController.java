package com.dev.zssn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.zssn.dto.SurvivorDto;
import com.dev.zssn.dto.TradeDto;
import com.dev.zssn.services.TradeService;

@RestController
public class TradeController {

  final TradeService tradeService;

  public TradeController(final TradeService tradeService) {
    this.tradeService = tradeService;
  }

  final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

  @PostMapping("trade")
  public ResponseEntity<List<SurvivorDto>> trade(@RequestBody final TradeDto trade) {
    try {
      final List<SurvivorDto> survivors = this.tradeService.trade(trade);
      final HttpStatus status = survivors.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE;
      return new ResponseEntity<>(survivors, status);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
