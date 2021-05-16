package com.mmcbrien.montyhall;

import com.mmcbrien.montyhall.strategy.Strategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Host {
    
    private final List<Door> doors;
    private final int numInitialDoors;
    private Door selectedDoor;
    private Door prizeDoor;
    private Door revealedDoor;
    
    private final Random rng = new Random();
    
    public Host(int numDoors) {
        /*
        doors will be a linked list with the following pattern.
        a) When nothing has happened. No guaranteed order:
        door -> door -> door
        b) When a door has been selected and no reveal:
        door (selected) -> door -> door
        c) when a door has been selected and a door has been revealed:
        door (revealed) -> door (selected) -> door
         */
        doors = new LinkedList<>();
        this.numInitialDoors = numDoors;
    }
    
    public void setUpGame() {
        for (int i = 0; i < numInitialDoors; i++) {
            doors.add(new Door(i, Door.PRIZE_OPTION.GOAT));
        }

        int prizeDoorIndex = rng.nextInt(numInitialDoors);
        doors.get(prizeDoorIndex).setPrize(Door.PRIZE_OPTION.CAR);
        prizeDoor = doors.get(prizeDoorIndex);
    }
    
    public Door.PRIZE_OPTION revealSelectedPrize() {
        return selectedDoor.getPrize();
    }
    
    public void askForSelection(Strategy strategy) {
        
        Door doorToSelect;
        if (selectedDoor == null) {
            doorToSelect = strategy.selectInitialDoor(doors);
        } else {
            doorToSelect = strategy.selectDoor(doors.subList(1, doors.size()));
            selectedDoor.deselect();
        }
        selectedDoor = doorToSelect;
        selectedDoor.select();
        doors.remove(selectedDoor);
        doors.add(0, selectedDoor);
    }
    
    
    public void revealDoor() {
        List<Door> revealableDoors = new ArrayList<>(doors.subList(1, doors.size()));
        revealableDoors.remove(prizeDoor);
        int doorIndexToReveal = rng.nextInt(revealableDoors.size());

        Door doorToReveal = revealableDoors.get(doorIndexToReveal);
        doorToReveal.reveal();
        doors.remove(doorToReveal);
        doors.add(0, doorToReveal);
    }
    
}
