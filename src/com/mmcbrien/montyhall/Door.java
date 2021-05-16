package com.mmcbrien.montyhall;

public class Door {
    
    public enum PRIZE_OPTION {
        GOAT,
        CAR
    }
    
    private PRIZE_OPTION prize;
    private boolean isSelected = false;
    private boolean isRevealed = false;
    
    public Door() {
        
    }
    
    public Door(PRIZE_OPTION prize) {
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
}
