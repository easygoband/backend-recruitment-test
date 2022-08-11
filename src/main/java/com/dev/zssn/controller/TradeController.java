package com.dev.zssn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<TradeDto> trade(@RequestBody final TradeDto trade) {
    try {
      this.tradeService.trade(trade);
      return new ResponseEntity<>(trade, HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
