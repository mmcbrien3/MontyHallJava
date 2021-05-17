package com.mmcbrien.montyhall.door;

import java.util.Objects;

public class Door {
    
    public enum PRIZE_OPTION {
        GOAT,
        CAR
    }
    
    private PRIZE_OPTION prize;
    private final int doorNumber;
    private boolean isSelected = false;
    private boolean isRevealed = false;
    
    
    public Door(int doorNumber, PRIZE_OPTION prize) {
        this.doorNumber = doorNumber;
        this.prize = prize;
    }

    public void setPrize(PRIZE_OPTION prize) {
        this.prize = prize;
    }

    public PRIZE_OPTION getPrize() {
        return prize;
    }
    
    public void reveal() {
        this.isRevealed = true;
    }
    
    public void select() {
        this.isSelected = true;
    }
    
    public void deselect() {
        this.isSelected = false;
    }

    public boolean isRevealed() {
        return isRevealed;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public int getDoorNumber() {
        return doorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Door door = (Door) o;
        return doorNumber == door.doorNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doorNumber);
    }
}
