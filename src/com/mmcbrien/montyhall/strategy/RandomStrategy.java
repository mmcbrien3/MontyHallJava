package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.Door;
import com.mmcbrien.montyhall.DoorUtil;

import java.util.List;

public class RandomStrategy implements Strategy {
    
    public Door selectDoor(List<Door> doors) {
        return DoorUtil.getRandomDoor(doors);
    }
}
