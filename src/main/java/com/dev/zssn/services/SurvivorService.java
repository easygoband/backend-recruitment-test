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
import com.dev.zssn.repository.SurvivorRepository;

@Service
public class SurvivorService {

  private final PositionRepository positionRepository;
  private final SurvivorRepository survivorRepository;
  private final AssetRepository assetRepository;

  public SurvivorService(
    final PositionRepository positionRepository,
    final SurvivorRepository survivorRepository,
    final AssetRepository assetRepository
  ) {
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
    final List<SurvivorAsset> inventory = this.getInventory(dto.getInventory());

    final SurvivorConverter converter = new SurvivorConverter();
    final Survivor survivor = converter.toModel(dto, position, inventory);
    return saveSurvivor(survivor);
  }

  private List<SurvivorAsset> getInventory(final List<InventoryDto> inventoryDto) {
    final InventoryConverter converter = new InventoryConverter();
    return inventoryDto == null ? new ArrayList<>() :
    inventoryDto
    .stream()
    .map(i -> {
      final Asset asset = this.getAssetByName(i.getAsset());
      return asset == null ? null: converter.toModel(i, asset);
    })
    .filter(i -> i != null)
    .collect(Collectors.toList());
  }

  private SurvivorDto saveSurvivor(final Survivor survivor) {
    final SurvivorConverter converter = new SurvivorConverter();
    final Survivor saved = survivorRepository.save(survivor);
    return converter.toDto(saved);
  }

  public SurvivorDto addInventory(final Long survivorId, final List<SurvivorAsset> inventory) {
    final Survivor survivor = this.getSurvivorById(survivorId);
    survivor.setInventory(inventory);
    return this.saveSurvivor(survivor);
  }

  public Survivor getSurvivorById(final Long survivorId) {
    return this.survivorRepository.findById(survivorId).get();
  }

  public PositionDto updateSurvivorPosition(final Long survivorId, final PositionDto positionDto) {
    final Survivor survivor = this.getSurvivorById(survivorId);
    final PositionConverter converter = new PositionConverter();
    final Position position = this.positionRepository.save(converter.updateModel(survivor.getLastPosition(), positionDto));
    return converter.toDto(position);
  }

  public SurvivorDto reportInfection(final Long survivorId) {
    final Survivor survivor = this.getSurvivorById(survivorId);
    final Integer reports = survivor.getInfectionReports();
    survivor.setInfectionReports((reports == null ? 0 : reports)+ 1);
    survivor.setIsInfected(survivor.getInfectionReports() >= 3);
    this.survivorRepository.save(survivor);
    return this.getSurvivor(survivorId);
  }

  private SurvivorDto getSurvivor(final Long survivorId) {
    final Survivor survivor = this.getSurvivorById(survivorId);
    final SurvivorConverter converter = new SurvivorConverter();
    return converter.toDto(survivor);
  }

  private Position savePosition(final PositionDto positionDto) {
    final PositionConverter positionConverter = new PositionConverter();
    final Position position = positionConverter.toModel(positionDto);
    return positionRepository.save(position);
  }

  private Asset getAssetByName(final AssetDto assetDto) {
    final List<Asset> list = this.assetRepository.findByName(assetDto.getName());
    return list != null && !list.isEmpty() ? list.get(0) : null;
  }

}
