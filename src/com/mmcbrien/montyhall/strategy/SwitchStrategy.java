package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.door.Door;
import com.mmcbrien.montyhall.door.IDoorCollection;

public class SwitchStrategy implements IStrategy {

    public Door selectDoor(IDoorCollection doors) {
        return getRandomDoor(doors.getUnselectedUnrevealedDoors());
    }
}
