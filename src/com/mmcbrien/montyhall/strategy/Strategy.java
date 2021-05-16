package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.Door;
import com.mmcbrien.montyhall.DoorUtil;

import java.util.List;
import java.util.Random;

public interface Strategy {
    
    public Door selectDoor(List<Door> doors);
    
    public default Door selectInitialDoor(List<Door> doors) {
        return DoorUtil.getRandomDoor(doors);
    }
}
