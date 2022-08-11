package com.dev.zssn.dto;

import java.util.List;

public class SurvivorDto {

  private Long id;
  private String name;
  private String age;
  private String gender;
  private PositionDto lastPosition;
  private Boolean isInfected;
  private List<InventoryDto> inventory;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }  

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

  public PositionDto getLastPosition() {
    return lastPosition;
  }

  public void setLastPosition(PositionDto lastPosition) {
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