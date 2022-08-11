package com.dev.zssn.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.zssn.dto.InventoryDto;
import com.dev.zssn.dto.TraderDto;
import com.dev.zssn.models.Survivor;
import com.dev.zssn.models.SurvivorAsset;
import com.dev.zssn.repository.SurvivorRepository;
import com.dev.zssn.trade.TradeAsset;
import com.dev.zssn.trade.Trader;

@Service
public class TraderService {

  private final SurvivorRepository survivorRepository;
  
  public TraderService(final SurvivorRepository survivorRepository) {
    this.survivorRepository = survivorRepository;
  }

  public Trader getTrader(final TraderDto trader) {
    final Survivor survivor = survivorRepository.findById(trader.getSurvivorId()).get();
    final List<TradeAsset> tradeAssets = getTradeAssets(survivor, trader);
    return new Trader(survivor.getId(), tradeAssets, survivor.getIsInfected());
  }

  private List<TradeAsset> getTradeAssets(final Survivor survivor, final TraderDto trader) {
    return survivor.getInventory()
    .stream()
    .map(asset -> getTradeAsset(asset, trader.getItems()))
    .filter(i -> i != null)
    .collect(Collectors.toList());
  }

  private TradeAsset getTradeAsset (final SurvivorAsset asset, final List<InventoryDto> tradeItems) {
    final List<InventoryDto> items = tradeItems
    .stream()
    .filter(e -> e.getAsset().getName().equals(asset.getAsset().getName()))
    .collect(Collectors.toList());
    return items.isEmpty() ? null : new TradeAsset(asset.getAsset(), asset.getAmount(), items.get(0).getAmount());
  }
}
