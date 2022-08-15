package com.dev.zssn.dto;

public class AssetAverageDto {
  final private String name;
  final private Double avg;

  public AssetAverageDto(final String name, final Double avg) {
    this.name = name;
    this.avg = avg;
  }

  public String getName() {
    return name;
  }

  public Double getAvg() {
    return avg;
  }

}
