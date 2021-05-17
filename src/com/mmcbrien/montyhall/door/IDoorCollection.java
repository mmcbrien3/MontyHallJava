package com.mmcbrien.montyhall.door;

import java.util.List;
import java.util.Random;

/**
 * The IDoorCollection holds a collection of {@link Door} objects and defines the basic
 * operations that can be taken against them.
 */
public interface IDoorCollection {
    
    Random random = new Random();
    
    void setupDoors(int numDoors);
    
    List<Door> getDoors();
    
    void selectDoor(Door door);

    void deselectDoor(Door door);
    
    void revealDoor(Door door);
    
    List<Door> getRevealableDoors();
    
    List<Door> getSelectableDoors();
    
    List<Door> getUnselectedUnrevealedDoors();
    
    Door getSelectedDoor();
    
}
