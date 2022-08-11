package com.dev.zssn.dto;

import java.util.List;

public class TraderDto {

  private Long survivorId;
  private List<InventoryDto> items;

  public Long getSurvivorId() {
    return survivorId;
  }

  public void setSurvivorId(Long survivorId) {
    this.survivorId = survivorId;
  }

  public List<InventoryDto> getItems() {
    return items;
  }

  public void setItems(List<InventoryDto> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "TraderDto [items=" + items + ", survivorId=" + survivorId + "]";
  }

}
