package com.dev.zssn.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.dev.zssn.converter.InventoryConverter;
import com.dev.zssn.dto.AssetAverageDto;
import com.dev.zssn.dto.InventoryDto;
import com.dev.zssn.dto.ReportDto;
import com.dev.zssn.models.Survivor;
import com.dev.zssn.models.SurvivorAsset;
import com.dev.zssn.repository.SurvivorRepository;

@Service
public class ReportService {

  private final SurvivorRepository survivorRepository;
  private final AssetService assetService;

  public ReportService(
    final SurvivorRepository survivorRepository,
    final AssetService assetService
  ) {
    this.survivorRepository = survivorRepository;
    this.assetService = assetService;
  }

  public ReportDto report() {
    final List<Survivor> survivors = this.survivorRepository.findAll();
    final int infectedPercentage = this.percentageByStatus(survivors, true);
    final int notInfectedPercentage = this.percentageByStatus(survivors, false);
    final List<AssetAverageDto> assetsAvg = this.getAssetsAvg(survivors);
    final int lostPoints = this.lostPoints(survivors);
    return new ReportDto(infectedPercentage, notInfectedPercentage, assetsAvg, lostPoints);
  }

  private List<AssetAverageDto> getAssetsAvg(final List<Survivor> survivors) {
    final int notInfected = survivorsNumberByStatus(survivors, false);
    System.out.println(notInfected);
    return this.getInventory(survivors)
    .stream()
    .map(i -> this.getAssetAvg(i, notInfected))
    .collect(Collectors.toList());
  }

  private AssetAverageDto getAssetAvg(final InventoryDto i,  final int notInfected) {
    final double avg = notInfected > 0 ? Double.parseDouble(i.getAmount() + "") / notInfected : 0;
    return new AssetAverageDto(i.getAsset().getName(), avg);
  }

  private int lostPoints(final List<Survivor> survivors) {
    final List<Survivor> infected = filterByStatus(survivors, true);
    return infected
    .stream()
    .map(s -> this.getSurvivorPoints(s))
    .reduce((a, c) -> a + c)
    .get();
  }

  private int getSurvivorPoints(final Survivor survivor) {
    if (survivor.getInventory().isEmpty()) {
      return 0;
    }

    return survivor.getInventory()
    .stream()
    .map(a -> a.getAmount() * a.getAsset().getPoints())
    .reduce((a, c) -> a + c)
    .get();
  }

  private List<InventoryDto> getInventory(final List<Survivor> survivors) {
    HashMap<String, InventoryDto> assets = this.getAssets();
    final List<Survivor> filtered = this.filterByStatus(survivors, false);

    if (filtered.isEmpty()) {
      return new ArrayList<>(assets.values());  
    }

    filtered
    .stream()
    .map(s -> s.getInventory())
    .reduce((a, c) -> Stream.concat(a.stream(), c.stream()).collect(Collectors.toList()))
    .get()
    .forEach(a -> this.sumAmount(assets, a));
    return new ArrayList<>(assets.values());
  }

  private HashMap<String, InventoryDto> getAssets() {
    HashMap<String, InventoryDto> assets = new HashMap<>();
    final InventoryConverter converter = new InventoryConverter();
    this.assetService.all()
    .forEach(a -> assets.put(a.getName(), converter.toDto(a, 0)));
    return assets;
  }

  private void sumAmount(HashMap<String, InventoryDto> assets, final SurvivorAsset asset) {
    final InventoryDto assetDto = assets.get(asset.getAsset().getName());
    if (assetDto != null) {
      assetDto.setAmount(assetDto.getAmount() + asset.getAmount());
    }
  }

  private int percentageByStatus(final List<Survivor> survivors, final boolean infected) {
    return (this.survivorsNumberByStatus(survivors, infected) * 100) / survivors.size();
  }

  private int survivorsNumberByStatus(final List<Survivor> survivors, final boolean infected) {
    final List<Survivor> infectedSurvivors = filterByStatus(survivors, infected);
    return infectedSurvivors.size();
  }

  private List<Survivor> filterByStatus(final List<Survivor> survivors, final boolean infected) {
    return survivors
    .stream()
    .filter(s -> infected ? s.getIsInfected() : !s.getIsInfected() )
    .collect(Collectors.toList());
  }

}
