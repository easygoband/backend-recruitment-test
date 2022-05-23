package com.easy.zssn.requestObjects;

import com.easy.zssn.model.Inventory;
import com.easy.zssn.model.Location;
import com.easy.zssn.model.Survivor;

public class SurvivorRO {
    public Survivor newSurvivor;
    public Location newLocation;
    public Inventory newInventory;


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
