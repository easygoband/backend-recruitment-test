package com.dev.zssn.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Survivor {

  private @Id @GeneratedValue Long id;

  private String name;

  private String age;

  private String gender;

  @OneToOne()
  private Position lastPosition;

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

}
