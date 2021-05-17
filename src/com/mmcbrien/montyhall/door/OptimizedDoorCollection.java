package com.mmcbrien.montyhall.door;

import java.util.ArrayList;
import java.util.List;

/**
 * The OptimizedDoorCollection implements the {@link IDoorCollection}. It is a more performant implementation
 * of this interface by exploiting the fact that there are a finite amount of states that the system can be.
 *
 * The time-consuming operations performed during the game will include:
 * - Inserting all doors onto list
 *  - Insertion into linked list is O(1)
 * - Selecting a door from the initial doors
 *  - Access on linked list is O(n) :(
 * - Revealing a door from the possible doors
 *  - This is unoptimized currently
 * - Selecting a door from remaining doors
 *  - This is the most optimized part of this class. This class optimizes this by preparing the data set in advance
 *  for this operation. When this stage is reached, the collection of doors will always be in the following
 *  order: door 1 (revealed) -> door 2 (selected) -> door 3 -> door 4 -> ... -> door N. Therefore, selecting the door
 *  that is already selected is O(1) and returning the list of possible doors that can be selected for the other
 *  strategies is also O(1). For a naive implementation, both of these operations would be O(n)
 */
public class OptimizedDoorCollection implements IDoorCollection {
    
    private final ArrayList<Door> doors = new ArrayList<>();
    
    @Override
    public void setupDoors(int numDoors)  {
        for (int i = 0; i < numDoors; i++) {
            doors.add(new Door(i, Door.PRIZE_OPTION.GOAT));
        }

        doors.get(0).setPrize(Door.PRIZE_OPTION.CAR);
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
        return doors.get(0).getPrize().equals(Door.PRIZE_OPTION.CAR) ? 
                doors.subList(1, doors.size()) : 
                doors.subList(2, doors.size());
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
