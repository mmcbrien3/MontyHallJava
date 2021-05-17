package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.door.Door;
import com.mmcbrien.montyhall.door.IDoorCollection;

public class StayStrategy implements IStrategy {

    public Door selectDoor(IDoorCollection doors) {
        return doors.getSelectedDoor();
    }
}
