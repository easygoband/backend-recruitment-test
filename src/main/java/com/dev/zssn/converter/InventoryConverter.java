package com.dev.zssn.converter;

import com.dev.zssn.dto.AssetDto;
import com.dev.zssn.dto.InventoryDto;
import com.dev.zssn.models.Asset;
import com.dev.zssn.models.SurvivorAsset;

public class InventoryConverter {

  public SurvivorAsset toModel(final InventoryDto dto, final Asset asset) {
    final SurvivorAsset inventory = new SurvivorAsset();
    inventory.setAsset(asset);
    inventory.setAmount(dto.getAmount());
    return inventory;
  }

  public SurvivorAsset toModel(final Asset asset, final Integer amount) {
    final SurvivorAsset inventory = new SurvivorAsset();
    inventory.setAsset(asset);
    inventory.setAmount(amount);
    return inventory;
  }

  public InventoryDto toDto(final SurvivorAsset inventory) {
    final InventoryDto dto = new InventoryDto();
    final AssetConverter assetConverter = new AssetConverter();
    final AssetDto asset = assetConverter.toDto(inventory.getAsset());
    dto.setAsset(asset);
    dto.setAmount(inventory.getAmount());
    return dto;
  }

}
