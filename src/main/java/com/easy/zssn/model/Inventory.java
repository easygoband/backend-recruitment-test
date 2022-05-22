package com.easy.zssn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Inventory {
    @Id
    @GeneratedValue
    Integer id;

    Integer survivorID;
    String itemName;
    int itemCount;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSurvivorID() {
        return survivorID;
    }
    public void setSurvivorID(Integer survivorID) {
        this.survivorID = survivorID;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

}
