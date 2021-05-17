package com.mmcbrien.montyhall;

import com.mmcbrien.montyhall.door.Door;
import com.mmcbrien.montyhall.door.IDoorCollection;
import com.mmcbrien.montyhall.door.NaiveDoorCollection;
import com.mmcbrien.montyhall.door.OptimizedDoorCollection;
import com.mmcbrien.montyhall.strategy.IStrategy;

import java.util.List;
import java.util.Random;

public class Host {
    
    private IDoorCollection doorCollection;
    
    private final Random rng = new Random();
    
    public Host(int numDoors) {
        doorCollection = new NaiveDoorCollection();
        doorCollection.setupDoors(numDoors);
    }
 
    public Door.PRIZE_OPTION revealSelectedPrize() {
        return doorCollection.getSelectedDoor().getPrize();
    }
    
    public void askForSelection(IStrategy IStrategy) {
        
        Door doorToSelect;
        Door selectedDoor = doorCollection.getSelectedDoor();
        if (doorCollection.getSelectedDoor() == null) {
            doorToSelect = IStrategy.selectInitialDoor(doorCollection);
        } else {
            doorToSelect = IStrategy.selectDoor(doorCollection);
        }
        
        if (selectedDoor == null) {
            doorCollection.selectDoor(doorToSelect);
        } else if (selectedDoor != doorToSelect) {
            doorCollection.deselectDoor(selectedDoor);
            doorCollection.selectDoor(doorToSelect);
        }
    }
    
    
    public void revealDoor() {
        List<Door> revealableDoors = doorCollection.getRevealableDoors();
        int doorIndexToReveal = rng.nextInt(revealableDoors.size());

        Door doorToReveal = revealableDoors.get(doorIndexToReveal);
        doorCollection.revealDoor(doorToReveal);
    }
    
}
