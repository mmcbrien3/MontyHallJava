package com.mmcbrien.montyhall.strategy;

import com.mmcbrien.montyhall.door.Door;
import com.mmcbrien.montyhall.door.IDoorCollection;

import java.util.List;
import java.util.Random;

public interface IStrategy {
    
    Random random = new Random();
    
    Door selectDoor(IDoorCollection doors);
    
    default Door selectInitialDoor(IDoorCollection doors) {
        return getRandomDoor(doors.getDoors());
    }
    
    default Door getRandomDoor(List<Door> doors) {
        return doors.get(random.nextInt(doors.size()));
    }
}
