package com.dev.zssn.trade;

import java.util.List;

public class SurvivorTrade {

  private List<TradeAsset> assets;
  private Long survivorId;
  private boolean isInfected;

  public SurvivorTrade(Long survivorId, List<TradeAsset> assets, boolean isInfected) {
    this.survivorId = survivorId;
    this.assets = assets;
    this.isInfected = isInfected;
  }

  public List<TradeAsset> getAssets() {
    return assets;
  }

  public Long getSurvivorId() {
    return survivorId;
  }

  public Integer getTradeValue() {
    return assets == null || assets.isEmpty() ? 0 : assets
    .stream()
    .map(asset -> asset.getTradeValue())
    .reduce(0, (a, b) -> a + b);
  }

  public boolean isAvailableToTrade() {
    return isInfected || assets == null || assets.isEmpty() ? false :
    assets
    .stream()
    .map(asset -> asset.isAvailableToTrade())
    .reduce(true, (a, c) -> a && c);
  }

  @Override
  public String toString() {
    return "[\n  assets=" + assets + ",\n  survivorId=" + survivorId + ",\n  isAvailableToTrade=" + isAvailableToTrade() + "\n ]";
  }  

}
