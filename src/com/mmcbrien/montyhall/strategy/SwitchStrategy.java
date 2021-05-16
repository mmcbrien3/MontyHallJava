package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.Door;
import com.mmcbrien.montyhall.DoorUtil;

import java.util.List;

public class SwitchStrategy implements Strategy {

    public Door selectDoor(List<Door> doors) {
        List<Door> unselectedDoors = DoorUtil.getUnselectedDoors(doors); 
        return DoorUtil.getRandomDoor(unselectedDoors);
    }
}
