package com.mmcbrien.montyhall.door;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NaiveDoorCollection implements IDoorCollection {
    
    private final ArrayList<Door> doors = new ArrayList<>();
    
    @Override
    public void setupDoors(int numDoors)  {
        for (int i = 0; i < numDoors; i++) {
            doors.add(new Door(i, Door.PRIZE_OPTION.GOAT));
        }

        int prizeDoorIndex = random.nextInt(numDoors);
        doors.get(prizeDoorIndex).setPrize(Door.PRIZE_OPTION.CAR);
    }
    
    @Override
    public List<Door> getDoors() {
        return doors;
    }
    
    @Override
    public void selectDoor(Door door) {
        door.select();
    }
    
    @Override
    public void deselectDoor(Door door) {
        door.deselect();
    }

    @Override
    public void revealDoor(Door door) {
        door.reveal();
    }

    @Override
    public List<Door> getRevealableDoors() {
        return doors.stream().filter(
                d -> !d.isSelected() && !d.isRevealed() && d.getPrize().equals(Door.PRIZE_OPTION.GOAT)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Door> getSelectableDoors() {
        return doors.stream().filter(d -> !d.isRevealed()).collect(Collectors.toList());
    }

    @Override
    public List<Door> getUnselectedUnrevealedDoors() {
        return doors.stream().filter(d -> !d.isSelected() && !d.isRevealed()).collect(Collectors.toList());
    }

    @Override
    public Door getSelectedDoor() {
        List<Door> selectedDoors = doors.stream().filter(Door::isSelected).collect(Collectors.toList());
        if (selectedDoors.size() == 0) {
            return null;
        } else if (selectedDoors.size() > 1) {
            throw new IllegalStateException("Cannot have more than 1 door selected at a time.");
        }
        return selectedDoors.get(0);
    }
}
