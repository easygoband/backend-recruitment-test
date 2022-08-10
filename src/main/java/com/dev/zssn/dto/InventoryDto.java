package com.dev.zssn.dto;

public class InventoryDto {

  private AssetDto asset;
  private Integer amount;

  public AssetDto getAsset() {
    return asset;
  }

  public void setAsset(AssetDto asset) {
    this.asset = asset;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

}
