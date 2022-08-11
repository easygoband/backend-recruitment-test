package com.dev.zssn.converter;

import com.dev.zssn.dto.AssetDto;
import com.dev.zssn.models.Asset;

public class AssetConverter {

  public Asset toModel(final AssetDto dto) {
    final Asset asset = new Asset();
    asset.setName(dto.getName());
    asset.setPoints(dto.getPoints());
    return asset;
  }

  public AssetDto toDto(final Asset asset) {
    final AssetDto dto = new AssetDto();
    dto.setName(asset.getName());
    dto.setPoints(asset.getPoints());
    return dto;
  }

}
