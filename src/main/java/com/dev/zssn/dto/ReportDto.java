package com.dev.zssn.dto;

import java.util.List;

public class ReportDto {

  private final String infectedPercentage;
  private final String notInfectedPercentage;
  private final List<AssetAverageDto> assetsAverage;
  private final Integer lostAssetsPoints;

  public ReportDto(
    final int infectedPercentage,
    final int notInfectedPercentage,
    final List<AssetAverageDto> assetsAverage,
    final Integer lostAssetsPoints
  ) {
    this.infectedPercentage = infectedPercentage + " %";
    this.notInfectedPercentage = notInfectedPercentage + " %";;
    this.assetsAverage = assetsAverage;
    this.lostAssetsPoints = lostAssetsPoints;
  }

  public String getInfectedPercentage() {
    return infectedPercentage;
  }

  public String getNotInfectedPercentage() {
    return notInfectedPercentage;
  }

  public List<AssetAverageDto> getAssetsAverage() {
    return assetsAverage;
  }

  public Integer getLostAssetsPoints() {
    return lostAssetsPoints;
  }

}
