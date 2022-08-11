package com.dev.zssn.trade;

import com.dev.zssn.models.Asset;

public class AssetTrade {
  private Asset asset;
  private Integer inventoryAmount;
  private Integer tradeAmount;

  public AssetTrade(Asset asset, Integer inventoryAmount, Integer tradeAmount) {
    this.asset = asset;
    this.inventoryAmount = inventoryAmount;
    this.tradeAmount = tradeAmount;
  }

  public Asset getAsset() {
    return asset;
  }

  public Integer getInventoryAmount() {
    return inventoryAmount;
  }

  public Integer getTradeAmount() {
    return tradeAmount;
  }

  public Integer getTradeValue() {
    return asset.getPoints() * tradeAmount;
  }

  public boolean isAvailableToTrade() {
    return this.inventoryAmount >= tradeAmount;
  }

  @Override
  public String toString() {
    return "\n   [\n    asset=" + asset + ",\n    inventoryAmount=" + inventoryAmount + ",\n    tradeAmount=" + tradeAmount + ",\n    tradeValue=" + getTradeValue() + ",\n    isAvailableToTrade=" + isAvailableToTrade() + "\n   ]\n";
  }

}
