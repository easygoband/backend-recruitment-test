package com.dev.zssn.dto;

import java.util.List;

public class SurvivorDto {

  private String name;
  private String age;
  private String gender;
  private LastPositionDto lastPosition;
  private Boolean isInfected;
  private List<InventoryDto> inventory;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LastPositionDto getLastPosition() {
    return lastPosition;
  }

  public void setLastPosition(LastPositionDto lastPosition) {
    this.lastPosition = lastPosition;
  }

  public Boolean getIsInfected() {
    return isInfected;
  }

  public void setIsInfected(Boolean isInfected) {
    this.isInfected = isInfected;
  }

  public List<InventoryDto> getInventory() {
    return inventory;
  }

  public void setInventory(List<InventoryDto> inventory) {
    this.inventory = inventory;
  }

}