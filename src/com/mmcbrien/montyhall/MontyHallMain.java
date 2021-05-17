package com.mmcbrien.montyhall;

import com.mmcbrien.montyhall.door.Door;
import com.mmcbrien.montyhall.strategy.RandomStrategy;
import com.mmcbrien.montyhall.strategy.StayStrategy;
import com.mmcbrien.montyhall.strategy.IStrategy;
import com.mmcbrien.montyhall.strategy.SwitchStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MontyHallMain {
    
    private static final int MIN_NUM_DOORS = 3;
    
    public static void main(String[] args) {

        int runsPerTest = 1_000_000;
        int maxNumDoors = 10;
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "data", "test_results.csv");

        List<Class<? extends IStrategy>> allStrategies
                = Arrays.asList(RandomStrategy.class, StayStrategy.class, SwitchStrategy.class);
        List<Integer> allNumDoors
                = IntStream.range(MIN_NUM_DOORS, maxNumDoors + 1).boxed().collect(Collectors.toList());

        try {
            FileWriter csvWriter = new FileWriter(filePath.toString());

            StringJoiner csvHeading = new StringJoiner(",");
            csvHeading.add("NumDoors");
            allStrategies.forEach(strat -> csvHeading.add(strat.getSimpleName()));

            csvWriter.write(csvHeading.toString());
            csvWriter.write("\n");

            for (int numDoors : allNumDoors) {
                csvWriter.write(Integer.toString(numDoors));
                csvWriter.write(",");
                for (Class<? extends IStrategy> strategyClass : allStrategies) {
                    System.out.println(
                            String.format("Running test for %s doors and %s strategy", 
                                    numDoors, strategyClass.getSimpleName()));
                    float winRate = runTest(strategyClass, numDoors, runsPerTest);
                    csvWriter.write(String.format("%.4f", winRate));
                    csvWriter.write(",");
                }
                csvWriter.write("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.err.printf("Error while writing to CSV. %s%n", e);
        }
    }
    
    public static float runTest(Class<? extends IStrategy> strategyClass, int numDoors, int runsPerTest) {
        int wins = 0;
        for (int i = 0; i < runsPerTest; i++) {
            try {
                Door.PRIZE_OPTION result = initAndRunGame(strategyClass.newInstance(), numDoors);
                if (result.equals(Door.PRIZE_OPTION.CAR)) {
                    wins += 1;
                }
            } catch (Exception e) {
                System.err.printf("Unexpected error while running game. %s%n", e);
            }
        }
        return (float) wins / (float)runsPerTest;
    }
    
    public static Door.PRIZE_OPTION initAndRunGame(IStrategy IStrategy, int numDoors) {
        Host host = new Host(numDoors);
        host.setUpGame();
        host.askForSelection(IStrategy);
        
        host.revealDoor();

        host.askForSelection(IStrategy);
        
        return host.revealSelectedPrize();
        
    }
}