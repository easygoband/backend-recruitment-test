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

    public Inventory(){}
    public Inventory(int id, int survivorID, String itemName, int itemCount){
        this.id = id;
        this.survivorID = survivorID;
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    @Override
    public String toString(){
        String resultado="";
        resultado+="ID:"+id+"\n";
        resultado+="survivorID:"+survivorID+"\n";
        resultado+="itemName:"+itemName+"\n";
        resultado+="itemCount:"+itemCount+"\n";
        return resultado;
    }

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
