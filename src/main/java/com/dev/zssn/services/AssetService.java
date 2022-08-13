package com.dev.zssn.services;

import org.springframework.stereotype.Service;

import com.dev.zssn.converter.AssetConverter;
import com.dev.zssn.dto.AssetDto;
import com.dev.zssn.models.Asset;
import com.dev.zssn.repository.AssetRepository;

@Service
public class AssetService {

  private final AssetRepository assetRepository;

  public AssetService(final AssetRepository assetRepository) {
    this.assetRepository = assetRepository;
  }

  public AssetDto addAsset(final AssetDto assetDto) {
    final AssetConverter converter = new AssetConverter();
    final Asset entity = converter.toModel(assetDto);
    final Asset asset = this.assetRepository.save(entity);
    return converter.toDto(asset);
  }

}
