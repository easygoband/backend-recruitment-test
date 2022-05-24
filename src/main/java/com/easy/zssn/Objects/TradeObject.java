package com.easy.zssn.Objects;

import com.easy.zssn.model.Inventory;

public class TradeObject {
    int survivorID1;
    Inventory[] inventory1;
    int survivorID2;
    Inventory[] inventory2;
    public int getSurvivorID1() {
        return survivorID1;
    }
    public void setSurvivorID1(int survivorID1) {
        this.survivorID1 = survivorID1;
    }
    public Inventory[] getInventory1() {
        return inventory1;
    }
    public void setInventory1(Inventory[] inventory1) {
        this.inventory1 = inventory1;
    }
    public int getSurvivorID2() {
        return survivorID2;
    }
    public void setSurvivorID2(int survivorID2) {
        this.survivorID2 = survivorID2;
    }
    public Inventory[] getInventory2() {
        return inventory2;
    }
    public void setInventory2(Inventory[] inventory2) {
        this.inventory2 = inventory2;
    }
}
