package com.dev.zssn.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dev.zssn.dto.InventoryDto;
import com.dev.zssn.dto.TradeDto;
import com.dev.zssn.dto.TraderDto;
import com.dev.zssn.models.Survivor;
import com.dev.zssn.models.SurvivorAsset;
import com.dev.zssn.repository.SurvivorRepository;
import com.dev.zssn.trade.AssetTrade;
import com.dev.zssn.trade.SurvivorTrade;
import com.dev.zssn.trade.Trade;

@Service
public class TradeService {

  final Logger LOGGER = LoggerFactory.getLogger(TradeService.class);

  private final SurvivorRepository survivorRepository;
  
  public TradeService(
    final SurvivorRepository survivorRepository
  ) {
    this.survivorRepository = survivorRepository;
  }

  public void trade(final TradeDto tradeDto) {
    final SurvivorTrade sender = this.getSurvivorTrade(tradeDto.getSender());
    final SurvivorTrade receiver = this.getSurvivorTrade(tradeDto.getReceiver());
    final Trade trade = new Trade(sender, receiver);
  }

  private SurvivorTrade getSurvivorTrade(final TraderDto trader) {
    final Survivor survivor = survivorRepository.findById(trader.getSurvivorId()).get();
    final List<AssetTrade> assetTrades = getAssetTrades(survivor, trader);
    return new SurvivorTrade(survivor.getId(), assetTrades, survivor.getIsInfected());
  }

  private List<AssetTrade> getAssetTrades(final Survivor survivor, final TraderDto trader) {
    return survivor.getInventory()
    .stream()
    .map(asset -> getAssetTrade(asset, trader.getItems()))
    .filter(i -> i != null)
    .collect(Collectors.toList());
  }

  private AssetTrade getAssetTrade (final SurvivorAsset asset, final List<InventoryDto> tradeItems) {
    final List<InventoryDto> items = tradeItems
    .stream()
    .filter(e -> e.getAsset().getName().equals(asset.getAsset().getName()))
    .collect(Collectors.toList());
    return items.isEmpty() ? null : new AssetTrade(asset.getAsset(), asset.getAmount(), items.get(0).getAmount());
  }

}
