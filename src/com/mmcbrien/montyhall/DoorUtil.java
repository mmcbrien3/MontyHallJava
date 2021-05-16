package com.mmcbrien.montyhall;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DoorUtil {
    
    public static Door getSelectedDoor(List<Door> doors) {
        List<Door> selectedDoors = doors.stream().filter(Door::isSelected).collect(Collectors.toList());
        if (selectedDoors.size() > 1) {
            throw new IllegalStateException("Cannot have more than one door selected at a time.");
        } else if (selectedDoors.size() == 0) {
            return null;
        }
        return selectedDoors.get(0);
    }
    
    public static List<Door> getUnselectedDoors(List<Door> doors) {
        return doors.stream().filter(door -> !door.isSelected()).collect(Collectors.toList());
    }
    
    public static Door getRandomDoor(List<Door> doors) {
        Random random = new Random();
        return doors.get(random.nextInt(doors.size()));
    }

    public static List<Door> getUnrevealedDoors(List<Door> doors) {
        return doors.stream().filter(d -> !d.isRevealed()).collect(Collectors.toList());
    }

    public static void deselectAllDoors(List<Door> doors) {
        doors.forEach(Door::deselect);
    }
}
