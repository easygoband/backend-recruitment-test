package com.dev.zssn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.zssn.converter.InventoryConverter;
import com.dev.zssn.dto.SurvivorDto;
import com.dev.zssn.dto.TradeDto;
import com.dev.zssn.models.Survivor;
import com.dev.zssn.models.SurvivorAsset;
import com.dev.zssn.trade.Trade;
import com.dev.zssn.trade.TradeAsset;
import com.dev.zssn.trade.Trader;

@Service
public class TradeService {

  private final SurvivorService survivorService;
  private final TraderService traderService;

  public TradeService(
    final SurvivorService survivorService,
    final TraderService traderService
  ) {
    this.survivorService = survivorService;
    this.traderService = traderService;
  }

  public List<SurvivorDto> trade(final TradeDto tradeDto) {
    final Trader sender = this.traderService.getTrader(tradeDto.getSender());
    final Trader receiver = this.traderService.getTrader(tradeDto.getReceiver());
    final Trade trade = new Trade(sender, receiver);
    if (trade.isAvailableToTrade()) {
      return this.doExchange(trade);
    }

    return new ArrayList<>();
  }

  private List<SurvivorDto> doExchange(final Trade trade) {
    final List<SurvivorDto> survivors = new ArrayList<>();
    final Trader sender = trade.getSender();
    final Trader receiver = trade.getReceiver();

    survivors.add(this.updateInventory(sender, receiver));
    survivors.add(this.updateInventory(receiver, sender));
    return survivors;
  }

  private SurvivorDto updateInventory(final Trader sender, final Trader receiver) {
    final Survivor survivor = this.survivorService.getSurvivorById(receiver.getSurvivorId());
    final List<SurvivorAsset> assets = survivor.getInventory();
    sender.getAssets().forEach(i -> this.updateAssets(assets, i, true));
    receiver.getAssets().forEach(i -> this.updateAssets(assets, i, false));

    return this.survivorService.addInventory(receiver.getSurvivorId(), assets);
  }

  private void updateAssets(List<SurvivorAsset> assets, TradeAsset trade, boolean add) {
    final SurvivorAsset asset = this.findAsset(assets, trade);
    if (asset != null) {
      int amount = asset.getAmount() + (add ? trade.getTradeAmount() : -trade.getTradeAmount());
      asset.setAmount(amount);
      return;
    }

    if (add) {
      final InventoryConverter converter = new InventoryConverter();
      assets.add(converter.toModel(trade.getAsset(), trade.getTradeAmount()));
    }
  }

  private SurvivorAsset findAsset(List<SurvivorAsset> survivorAssets, final TradeAsset asset) {
    List<SurvivorAsset> temp = survivorAssets
    .stream()
    .filter(receiverAsset -> receiverAsset.getAsset().getId() == asset.getAsset().getId())
    .collect(Collectors.toList());
    return temp.isEmpty() ? null : temp.get(0);
  }

}
