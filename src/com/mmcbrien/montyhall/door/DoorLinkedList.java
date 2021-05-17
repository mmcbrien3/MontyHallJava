package com.mmcbrien.montyhall.door;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DoorLinkedList implements IDoorCollection {

    
    private final LinkedList<Door> doors = new LinkedList<>();

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
        moveDoorToFrontOfList(door);
    }

    @Override
    public void deselectDoor(Door door) {
        door.deselect();
    }

    @Override
    public void revealDoor(Door door) {
        door.reveal();
        moveDoorToFrontOfList(door);
    }

    @Override
    public List<Door> getRevealableDoors() {
        return doors.stream().filter(
                d -> !d.isSelected() && !d.isRevealed() && d.getPrize().equals(Door.PRIZE_OPTION.GOAT)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Door> getSelectableDoors() {
        return doors.get(0).isRevealed() ? doors.subList(1, doors.size()) : doors;
    }

    @Override
    public List<Door> getUnselectedUnrevealedDoors() {
        if (doors.get(0).isRevealed() && doors.get(1).isSelected()) {
            return doors.subList(2, doors.size());
        } else if (doors.get(0).isSelected()) {
            return doors.subList(1, doors.size());
        } else {
            return doors;
        }
    }

    @Override
    public Door getSelectedDoor() {
        if (doors.get(0).isSelected()) {
            return doors.get(0);
        } else if (doors.get(1).isSelected()) {
            return doors.get(1);
        } else {
            return null;
        }
    }
    
    private void moveDoorToFrontOfList(Door door) {
        doors.remove(door);
        doors.add(0, door);
    }
}
