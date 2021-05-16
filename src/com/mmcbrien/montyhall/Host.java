package com.mmcbrien.montyhall;

import com.mmcbrien.montyhall.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Host {
    
    private final List<Door> doors;
    private final int numInitialDoors;
    
    private final Random rng = new Random();
    
    public Host(int numDoors) {
        doors = new ArrayList<>();
        this.numInitialDoors = numDoors;
    }
    
    public void setUpGame() {
        for (int i = 0; i < numInitialDoors; i++) {
            doors.add(new Door(Door.PRIZE_OPTION.GOAT));
        }

        int prizeDoorIndex = rng.nextInt(numInitialDoors);
        doors.get(prizeDoorIndex).setPrize(Door.PRIZE_OPTION.CAR);
    }
    
    public Door.PRIZE_OPTION revealSelectedPrize() {
        return DoorUtil.getSelectedDoor(doors).getPrize();
    }
    
    public void askForSelection(Strategy strategy) {
        List<Door> selectableDoors = DoorUtil.getUnrevealedDoors(doors);
        
        Door doorToSelect;
        if (selectableDoors.size() == numInitialDoors) {
            doorToSelect = strategy.selectInitialDoor(selectableDoors);
        } else {
            doorToSelect = strategy.selectDoor(selectableDoors);
        }
        DoorUtil.deselectAllDoors(doors);
        doorToSelect.select();
    }
    
    
    public void revealDoor() {
        List<Door> unselectedDoors = DoorUtil.getUnselectedDoors(doors);
        List<Door> unselectedGoatDoors = unselectedDoors.stream().filter(
                d -> d.getPrize().equals(Door.PRIZE_OPTION.GOAT)
        ).collect(Collectors.toList());
        int doorIndexToReveal = rng.nextInt(unselectedGoatDoors.size());

        unselectedGoatDoors.get(doorIndexToReveal).reveal();
    }
    
}
