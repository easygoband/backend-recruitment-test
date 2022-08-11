package com.dev.zssn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.zssn.converter.InventoryConverter;
import com.dev.zssn.converter.PositionConverter;
import com.dev.zssn.converter.SurvivorConverter;
import com.dev.zssn.dto.AssetDto;
import com.dev.zssn.dto.InventoryDto;
import com.dev.zssn.dto.PositionDto;
import com.dev.zssn.dto.SurvivorDto;
import com.dev.zssn.models.Asset;
import com.dev.zssn.models.Position;
import com.dev.zssn.models.Survivor;
import com.dev.zssn.models.SurvivorAsset;
import com.dev.zssn.repository.AssetRepository;
import com.dev.zssn.repository.PositionRepository;
import com.dev.zssn.repository.SurvivorAssetRepository;
import com.dev.zssn.repository.SurvivorRepository;

@Service
public class SurvivorService {

  private final SurvivorAssetRepository survivorAssetRepository;
  private final PositionRepository positionRepository;
  private final SurvivorRepository survivorRepository;
  private final AssetRepository assetRepository;
  
  SurvivorService(
    final SurvivorAssetRepository survivorAssetRepository,
    final PositionRepository positionRepository,
    final SurvivorRepository survivorRepository,
    final AssetRepository assetRepository
  ) {
    this.survivorAssetRepository = survivorAssetRepository;
    this.positionRepository = positionRepository;
    this.survivorRepository = survivorRepository;
    this.assetRepository = assetRepository;
  }

  public List<SurvivorDto> all() {
    final SurvivorConverter converter = new SurvivorConverter();
    List<Survivor> survivors = survivorRepository.findAll();
    List<SurvivorDto> dto = survivors.stream().map(s -> converter.toDto(s)).collect(Collectors.toList());
    return dto;
  }

  public SurvivorDto registerSurvivor(final SurvivorDto dto) {
    final Position position = this.savePosition(dto.getLastPosition());
    final List<SurvivorAsset> inventory = this.saveInventory(dto.getInventory());

    final SurvivorConverter converter = new SurvivorConverter();
    final Survivor survivor = converter.toModel(dto, position, inventory);
    final Survivor saved = survivorRepository.save(survivor);
    return converter.toDto(saved);
  }

  private Position savePosition(final PositionDto positionDto) {
    final PositionConverter positionConverter = new PositionConverter();
    final Position position = positionConverter.toModel(positionDto);
    return positionRepository.save(position);
  }

  private List<SurvivorAsset> saveInventory(final List<InventoryDto> inventoryDto) {
    return inventoryDto == null ? new ArrayList<>() :
    inventoryDto.stream().map(i -> this.saveSurvivorAsset(i))
    .filter(i -> i != null)
    .collect(Collectors.toList());
  }

  private SurvivorAsset saveSurvivorAsset(final InventoryDto inventoryDto) {
    final InventoryConverter converter = new InventoryConverter();
    final Asset asset = this.getAsset(inventoryDto.getAsset());
    if (asset != null) {
      final SurvivorAsset survivorAsset = converter.toModel(inventoryDto, asset);
      return survivorAssetRepository.save(survivorAsset);
    }
    return null;
  }

  private Asset getAsset(final AssetDto assetDto) {
    final List<Asset> list = this.assetRepository.findByName(assetDto.getName());
    return  list != null && !list.isEmpty() ? list.get(0) : null;
  }
  
}
