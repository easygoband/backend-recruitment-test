package com.dev.zssn.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dev.zssn.dto.TradeDto;
import com.dev.zssn.trade.Trader;
import com.dev.zssn.trade.Trade;

@Service
public class TradeService {

  final Logger LOGGER = LoggerFactory.getLogger(TradeService.class);

  private final TraderService traderService;
  
  public TradeService(final TraderService traderService) {
    this.traderService = traderService;
  }

  public void trade(final TradeDto tradeDto) {
    final Trader sender = this.traderService.getTrader(tradeDto.getSender());
    final Trader receiver = this.traderService.getTrader(tradeDto.getReceiver());
    final Trade trade = new Trade(sender, receiver);
  }

}
