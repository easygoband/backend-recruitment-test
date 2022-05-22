package com.easy.zssn.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    Integer survivorID;
    double latitude;
    double longitude;

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public Integer getSurvivorID() {
        return survivorID;
    }
    public void setSurvivorID(Integer survivorID) {
        this.survivorID = survivorID;
    }
   
}
