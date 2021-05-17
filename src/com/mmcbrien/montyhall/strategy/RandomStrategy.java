package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.door.Door;
import com.mmcbrien.montyhall.door.IDoorCollection;

public class RandomStrategy implements IStrategy {
    
    public Door selectDoor(IDoorCollection doors) {
        return getRandomDoor(doors.getSelectableDoors());
    }
}
