package com.easy.zssn.Objects;

public enum ItemPoints {
    Ammunition(1),
    Medication(2),
    Food(3),
    Water(4);
    public final int value;

    private ItemPoints(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
