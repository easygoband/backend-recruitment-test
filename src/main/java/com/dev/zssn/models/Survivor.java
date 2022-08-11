package com.dev.zssn.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Survivor {

  private @Id @GeneratedValue Long id;

  private String name;

  private String age;

  private String gender;

  private Integer infectionReports;

  @OneToOne()
  private Position lastPosition;

  @Column(name="is_infected")
  private Boolean isInfected;

  @OneToMany()
  private List<SurvivorAsset> inventory;

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

  public Position getLastPosition() {
    return lastPosition;
  }

  public void setLastPosition(Position lastPosition) {
    this.lastPosition = lastPosition;
  }

  public Boolean getIsInfected() {
    return isInfected;
  }

  public void setIsInfected(Boolean isInfected) {
    this.isInfected = isInfected;
  }

  public List<SurvivorAsset> getInventory() {
    return inventory;
  }

  public void setInventory(List<SurvivorAsset> inventory) {
    this.inventory = inventory;
  }

  public Integer getInfectionReports() {
    return infectionReports;
  }

  public void setInfectionReports(Integer infectionReports) {
    this.infectionReports = infectionReports;
  }

}
