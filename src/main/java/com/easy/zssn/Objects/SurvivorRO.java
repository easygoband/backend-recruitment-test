package com.easy.zssn.Objects;

import com.easy.zssn.model.Inventory;
import com.easy.zssn.model.Location;
import com.easy.zssn.model.Survivor;

public class SurvivorRO {
    public Survivor newSurvivor;
    public Location newLocation;
    public Inventory newInventory;
    public Inventory[] items;

    @Override
    public String toString(){
        String resultado = "";
        if(newSurvivor != null){
            resultado+="Survivor:"+newSurvivor.toString()+"\n";
        }
        if(newLocation != null){
            resultado+="Location:"+newLocation.toString()+"\n";
        }
        if(newInventory != null){
            resultado+="Inventory:"+newInventory.toString()+"\n";
        }
        if(items != null){
            for(Inventory inventory: items){
                resultado+= inventory.toString()+"\n";
            }
        }
        
        return resultado;
    }

    public Inventory[] getItems() {
        return items;
    }

    public void setItems(Inventory[] items) {
        this.items = items;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(Location newLocation) {
        this.newLocation = newLocation;
    }

    public Inventory getNewInventory() {
        return newInventory;
    }

    public void setNewInventory(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    public Survivor getNewSurvivor() {
        return newSurvivor;
    }

    public void setNewSurvivor(Survivor newSurvivor) {
        this.newSurvivor = newSurvivor;
    }

}
